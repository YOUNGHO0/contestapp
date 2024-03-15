package com.project.contestapplication.contest.controller;

import com.project.contestapplication.contest.controller.dto.request.ContestCreateDto;
import com.project.contestapplication.contest.controller.dto.request.ContestReadDto;
import com.project.contestapplication.contest.controller.dto.response.ContestReadResponseDto;
import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.service.ContestService;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/contest")
public class ContestController {
    private final ContestService contestService;
    //
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity createContest( @RequestBody ContestCreateDto contestCreateDto, @AuthenticationPrincipal User user){
        Contest contest = ContestCreateDto.toContest(contestCreateDto,user);
        contestService.createContest(contest);
        return ResponseEntity.ok().body("succes");
    }

    @GetMapping("")
    public ResponseEntity readContest(@RequestBody ContestReadDto contestReadDto){
        Contest contest = contestService.readContest(contestReadDto.getContestId());
        ContestReadResponseDto readResponseDto = ContestReadResponseDto.from(contest);
        return ResponseEntity.ok().body(readResponseDto);
    }

}
