package com.project.contestapplication.limiter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
@Slf4j
public class Bucket4jLimiter extends OncePerRequestFilter {

    Bucket bucket;
    public Bucket4jLimiter(){
        Bandwidth limit = Bandwidth.classic(2000, Refill.intervally(200, Duration.ofMillis(1000)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(bucket.tryConsume(1)){
            filterChain.doFilter(request,response);
        }
        else{
             setErrorResponse(response);
        }
    }

    @Data
    public static class ErrorResponse{
        private final Integer code;
        private final String message;
    }
    private void setErrorResponse( HttpServletResponse response){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(),"Server is now busy");
        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
