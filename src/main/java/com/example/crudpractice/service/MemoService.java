package com.example.crudpractice.service;

import com.example.crudpractice.entity.*;
import com.example.crudpractice.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MemoService {

    // 속성
    private final MemoRepository memoRepository;

    // 생성자
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    // 기능
    // 메모 생성 로직
    @Transactional
    public MemoCreateResponseDto createMemo(MemoCreateRequestDto requestDto) {

        Memo newMemo = new Memo(requestDto.getWriter(), requestDto.getTitle(), requestDto.getContent());

        Memo savedMemo = memoRepository.save(newMemo);

        return new MemoCreateResponseDto(savedMemo.getId(), savedMemo.getWriter(), savedMemo.getTitle(), savedMemo.getContent());
    }

    // 메모 단 건 조회 로직
    @Transactional(readOnly = true)
    public MemoDetailResponseDto getMemoDetail(Long memoId) {

        Memo foundMemo = memoRepository.findByIdAndIsDeletedFalse(memoId)
                .orElseThrow(() -> new RuntimeException("not found memo"));

        return new MemoDetailResponseDto(foundMemo.getId(), foundMemo.getWriter(), foundMemo.getTitle(), foundMemo.getContent());

    }

    // 메모 다건 조회 로직
    @Transactional(readOnly = true)
    public MemoListResponseDto getMemoList() {
        List<Memo> foundMemos = memoRepository.findByIsDeletedFalse();

        List<MemoListResponseDto.MemoDto> memoList = foundMemos.stream()
                .map(memo -> new MemoListResponseDto.MemoDto(memo.getId(),
                        memo.getWriter(),
                        memo.getTitle()))
                .toList();

        return new MemoListResponseDto(foundMemos.size(), memoList);
    }


    // 메모 수정 로직
    @Transactional
    public MemoUpdateResponseDto updateMemo(Long memoId, MemoUpdateRequestDto requestDto) {

        Memo foundMemo = memoRepository.findByIdAndIsDeletedFalse(memoId)
                .orElseThrow(() -> new RuntimeException("not found memo"));

        foundMemo.update(requestDto.getTitle(), requestDto.getContent());

        return new MemoUpdateResponseDto(memoId, foundMemo.getWriter(), requestDto.getTitle(), requestDto.getContent());
    }

    // 메모 삭제 로직
    @Transactional
    public MemoDeleteResponseDto deleteMemo(Long memoId) {

        Memo foundMemo = memoRepository.findByIdAndIsDeletedFalse(memoId)
                .orElseThrow(() -> new RuntimeException("not found memo"));

        foundMemo.softDelete();

        return new MemoDeleteResponseDto(memoId);
    }
}
