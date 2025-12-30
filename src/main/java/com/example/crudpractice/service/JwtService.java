package com.example.crudpractice.service;

import com.example.crudpractice.dto.AuthInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    private SecretKey secretKey;

    /**
     * 시크릿키 준비
     * 스프링빈 초기화 시점에 secretKey 생성
     */
    @PostConstruct
    void init() {
        byte[] decodedKeyByteList = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKeyByteList);
    }

    /**
     * 토큰 만들기
     */
    public String createJwt(Long memberId, String email) {

        // 데이터 준비
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 600); // 만료시간: 10분

        // 토큰 만들기
        String jwt = Jwts.builder()
                .issuer("jwt.basic.com")      // 발급자 : 토큰을 발급한 주체(서버)
                .subject (memberId.toString())    // 주체 : 이 토큰이 누구에 대한 것인지
                .expiration(expiration)          // 만료시간
                .issuedAt(now)                   // 토큰 발급 시갅
                .claim("email", email)        // JWT를 통해서 전달하고 싶은 내용 추가
                .signWith(secretKey)             // if you want to digitally sign or encrypt the JWT (Optionally)
                .compact();                      // to produce the resulting compact JWT string
        return jwt;
    }

    /**
     * 토큰 검증
     */
    public AuthInfo verifyToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            Claims claims = claimsJws.getPayload();

            Long memberId = Long.valueOf(claims.getSubject());
            String email = claims.get("email", String.class);

            AuthInfo authInfo = new AuthInfo(memberId, email);
            return authInfo;

        } catch (SignatureException e) {
            // 서명 불일치시 예외 처리
            throw new RuntimeException("서명 불일치");
        } catch (ExpiredJwtException e) {
            // 토큰 불일치시 예외 처리
            throw new RuntimeException("토큰 불일치");
        }
    }

}
