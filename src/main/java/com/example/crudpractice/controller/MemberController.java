package com.example.crudpractice.controller;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 컴포넌트 스캔으로 빈 등록
@RequestMapping("/members") // 요청 매핑으로 연결
public class MemberController {

    // 속성
    // 컨트롤러 레이어와 서비스 레이어 연결하기
    private final MemberService memberService;
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    // 생성자
    // DI (의존성 주입 3가지)
    // 1. 생성자 주입,
    // 2. Setter 주입
    // 3. 필드 주입
    // 생성자 주입이 좋은 이유 : 컨트롤러가 생성됨과 동시에 멤버 서비스가 고정되기 때문(변경될 위험 없음)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 생성 API
    @PostMapping
    public ResponseEntity<ApiResponse<MemberCreateResponseDto>> createMemberApi(@RequestBody MemberCreateRequestDto requestDto) {

        // 핵심 비지니스 로직
        MemberCreateResponseDto responseDto = memberService.createMember(requestDto);

        // 응답 반환
        ApiResponse<MemberCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        // ResponseEntity : 응답 객체를 잘 다룰 수 있게 스프링에서 지원해주는 response 클래스 / 제네릭 클래스임!
        ResponseEntity<ApiResponse<MemberCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 회원 단건 조회 API
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberDetailResponseDto>> getMemberDetailApi(@PathVariable("memberId") Long memberId) {

        MemberDetailResponseDto responseDto = memberService.getMemberDetail(memberId);

        ApiResponse<MemberDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<MemberDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 회원 다건 조회 API
    @GetMapping
    public ResponseEntity<ApiResponse<MemberListResponseDto>> getMemberList() {

        MemberListResponseDto memberList = memberService.getMemberList();

        ApiResponse<MemberListResponseDto> apiListResponse = new ApiResponse<>("success", 200, memberList);

        ResponseEntity<ApiResponse<MemberListResponseDto>> response = new ResponseEntity<>(apiListResponse, HttpStatus.OK);

        return response;

    }

    // 회원 수정 API
    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberUpdateResponseDto>> updateMemberApi(@PathVariable("memberId") Long memberId, @RequestBody MemberUpdateRequestDto requestDto) {

        MemberUpdateResponseDto responseDto = memberService.updateMember(memberId, requestDto);

        ApiResponse<MemberUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<MemberUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 회원 삭제 API
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberDeleteResponseDto>> deleteMemberApi(@PathVariable("memberId") Long memberId) {

        MemberDeleteResponseDto responseDto = memberService.deleteMember(memberId);

        ApiResponse<MemberDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<MemberDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}

