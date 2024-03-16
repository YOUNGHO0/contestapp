package com.project.contestapplication.contest.controller;

import com.google.gson.Gson;
import com.project.contestapplication.contest.controller.dto.request.ContestCreateDto;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.time.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs
class ContestControllerCreateTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserRepository userRepository;

    private ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    @BeforeMethod
    public void setUp(Method method) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        this.restDocumentation.beforeTest(getClass(), method.getName());
    }

    @AfterMethod
    public void tearDown() {
        this.restDocumentation.afterTest();
    }

    private Clock getFixedClock(LocalDateTime localDateTime){
        LocalDateTime dateTime = localDateTime;
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return Clock.fixed(instant, seoulZoneId);
    }

    public MockHttpSession getSavedUserSession(){
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        User savedUser = userRepository.save(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(savedUser, null,user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        MockHttpSession mockHttpSession =(MockHttpSession)request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return mockHttpSession;
    }


    @Test
    public void 글_작성_통합테스트() throws Exception {
        //given
        long nowPlus3Days = LocalDateTime.now().plusDays(3).toEpochSecond(ZoneOffset.UTC);
        long nowPlus5Days = LocalDateTime.now().plusDays(5).toEpochSecond(ZoneOffset.UTC);
        ContestCreateDto contestCreateDto = new ContestCreateDto("테스트","내용",nowPlus3Days,nowPlus5Days);
        String json = new Gson().toJson(contestCreateDto);
        //when then
        this.mockMvc.perform(post("/api/v1/contest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .session(getSavedUserSession())
                                .content(json)
                )

                .andExpect(status().isOk())

                .andDo(
                        document("contestCreate",
                                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),

                                requestFields(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("설명입니다"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("설명입니다"),
                                        fieldWithPath("startAt").type(JsonFieldType.NUMBER).description("설명입니다"),
                                        fieldWithPath("endAt").type(JsonFieldType.NUMBER).description("설명입니다")

                                )
                        )
                );

    }

    @Test
    public void 내용이_없으면_글작성이_불가능하다() throws Exception {
        //given
        long nowPlus3Days = LocalDateTime.now().plusDays(3).toEpochSecond(ZoneOffset.UTC);
        long nowPlus5Days = LocalDateTime.now().plusDays(5).toEpochSecond(ZoneOffset.UTC);
        ContestCreateDto contestCreateDto = new ContestCreateDto("테스트","",nowPlus3Days,nowPlus5Days);
        String json = new Gson().toJson(contestCreateDto);
        //when then
        this.mockMvc.perform(post("/api/v1/contest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .session(getSavedUserSession())
                        .content(json)
                )

                .andExpect(status().isBadRequest())
                .andDo(document("contestCreate",Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
                        )
                );

    }




}