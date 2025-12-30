package com.example.crudpractice.controller;

import com.example.crudpractice.dto.ApiResponse;
import com.example.crudpractice.dto.AuthInfo;
import com.example.crudpractice.dto.MemberLoginRequestDto;
import com.example.crudpractice.dto.MemberLoginResponseDto;
import com.example.crudpractice.service.AuthService;
import com.example.crudpractice.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // 속성
    private final AuthService authService;
    private final JwtService jwtService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    // 생성자
    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    // 기능
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberLoginResponseDto>> loginApi(@RequestBody MemberLoginRequestDto requestDto) {

        MemberLoginResponseDto token = authService.login(requestDto);

        ApiResponse<MemberLoginResponseDto> apiResponse = new ApiResponse<>("success", 200, token);

        ResponseEntity<ApiResponse<MemberLoginResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    @GetMapping("/check")
    public void checkJwt(@RequestParam("Authorization") String token) {
        AuthInfo authInfo = jwtService.verifyToken(token);

        Long memberId = authInfo.getMemberId();
        String email = authInfo.getEmail();

        log.info("memberId = {}", memberId);
        log.info("email = {}", email);
    }
}
