package com.example.crudpractice.service;

import com.example.crudpractice.dto.MemberLoginRequestDto;
import com.example.crudpractice.dto.MemberLoginResponseDto;
import com.example.crudpractice.entity.Member;
import com.example.crudpractice.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            MemberRepository memberRepository,
                       PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // 로그인 로직
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        // 1. 데이터 준비
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 2. 회원 조회 - 받은 email로 회원을 검색하기
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("member not find"));

        // 3. 비밀번호 검증 - 받아 온 비밀번호와 기존 회원의 비밀번호가 일치하는지 검증
        String encodedPassword = foundMember.getPassword();
        boolean match = passwordEncoder.matches(password, encodedPassword);

        if (!match) {
            throw new RuntimeException("invalid credentials");
        }

        // 4. 토큰 만들기
        // 베이직만 JWT 활용 보고 토큰 생성하기
        // 토큰에 어떤 데이터를 담아줘야할까? - email, name, id(pk: 식별자) / 단, 비밀번호 절대 안됨!!!

        String token = jwtService.createJwt(foundMember.getId(), email);

        log.info("token - {}", token);

        MemberLoginResponseDto responseDto = new MemberLoginResponseDto(token);

        return responseDto;

        // 인증인가
        // 1. 회원가입
        // 2. 로그인 -> 토큰 생성(발급)
        // 3. 인증정보 활용 -> 토큰 검증
    }
}
