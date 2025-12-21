package com.example.crudpractice.service;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.entity.Member;
import com.example.crudpractice.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    // 속성
    private final MemberRepository memberRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    // 생성자
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 생성 로직
    @Transactional
    public MemberCreateResponseDto createMember(MemberCreateRequestDto requestDto) {

        // 1. 데이터 준비
        String name = requestDto.getName();

        // 2. 회원 엔티티 생성(저장에 사용할 Member 엔티티 만들기)
        Member newMember = new Member(name);

        // 3. 레포지토리를 활용해서 Member 데이터 저장하기
        Member savedMember = memberRepository.save(newMember);

        // 4. 레이어 간 데이터가 움질일 때는 dto를 통해서!
        Long foundId = savedMember.getId();
        MemberCreateResponseDto responseDto = new MemberCreateResponseDto(foundId);

        // 5. 반환
        return responseDto;
    }

    // 회원 상세 조회 로직
    @Transactional(readOnly = true)
    public MemberDetailResponseDto getMemberDetail(Long memberId) {

        // 회원 상세 조회
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 반환을 담당해줄 Dto 클래스 생성하기
        Long foundMemberId = foundMember.getId();
        String foundMemberName = foundMember.getName();

        MemberDetailResponseDto responseDto = new MemberDetailResponseDto(foundMemberId, foundMemberName);

        // 반환
        return responseDto;
    }

    // 회원 다건 조회 로직
    @Transactional(readOnly = true)
    public MemberListResponseDto getMemberList() {

        // 데이터 조회
        List<Member> memberList = memberRepository.findByIsDeletedFalse();
        int count = memberList.size();

        // 내부 dto 만들기
        List<MemberListResponseDto.MemberDto> foundMemberListDto = new ArrayList<>();

        for (Member member : memberList) {

           Long foundMemberId = member.getId();
           String foundMemberName = member.getName();

           MemberListResponseDto.MemberDto response = new MemberListResponseDto.MemberDto(foundMemberId, foundMemberName);
           foundMemberListDto.add(response);
        }

        // 외부 dto 만들기
        MemberListResponseDto memberListResponseDto = new MemberListResponseDto(count,foundMemberListDto);

        // 반환
        return memberListResponseDto;
    }

    // 회원 수정 로직
    @Transactional
    public MemberUpdateResponseDto updateMember(Long memberId, MemberUpdateRequestDto requestDto) {

        // 회원 조회
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 회원 정보 수정 내용 조회
        String newMemberName = requestDto.getName();

        // 회원 정보 수정
        Member updatedMember = foundMember.update(newMemberName);

        // dto 만들기
        MemberUpdateResponseDto updateResponseDto = new MemberUpdateResponseDto(updatedMember.getId());

        // 반환
        return updateResponseDto;
    }

    // 회원 삭제 로직
    @Transactional
    public MemberDeleteResponseDto deleteMember(Long memberId) {

        // 회원 조회
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new RuntimeException("member not found"));

        // 삭제할 멤버 아이디 조회
        Long foundMemberId = foundMember.getId();

        // dto에 삭제할 멤버 아이디 넣기
        MemberDeleteResponseDto responseDto = new MemberDeleteResponseDto(foundMemberId);

        // 멤버 삭제
        foundMember.softDelete();

        // 반환
        return responseDto;
    }
}
