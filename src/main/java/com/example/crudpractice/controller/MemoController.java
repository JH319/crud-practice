package com.example.crudpractice.controller;

import com.example.crudpractice.entity.*;
import com.example.crudpractice.service.MemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
public class MemoController {

    // 속성
    private final MemoService memoService;
    private static final Logger log = LoggerFactory.getLogger(MemoController.class);

    // 생성자
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 기능
    // 메모 생성 API
    @PostMapping
    public ResponseEntity<ApiResponse<MemoCreateResponseDto>> createMemoApi(@RequestBody MemoCreateRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(memoService.createMemo(requestDto)));
    }

    // 메모 단 건 조회 API
    @GetMapping("/{memoId}")
    public ResponseEntity<ApiResponse<MemoDetailResponseDto>> getMemoDetailApi(@PathVariable("memoId") Long memoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(memoService.getMemoDetail(memoId)));
    }

    // 메모 다건 조회 API
    @GetMapping
    public ResponseEntity<ApiResponse<MemoListResponseDto>> getMemoListApi() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(memoService.getMemoList()));
    }

    // 메모 수정 API
    @PatchMapping("/{memoId}")
    public ResponseEntity<ApiResponse<MemoUpdateResponseDto>> updateMemoApi(@PathVariable("memoId") Long memoId, @RequestBody MemoUpdateRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(memoService.updateMemo(memoId, requestDto)));
    }

    // 메모 삭제 API
    @DeleteMapping("/{memoId}")
    public ResponseEntity<ApiResponse<MemoDeleteResponseDto>> deleteMemoApi(@PathVariable("memoId") Long memoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(memoService.deleteMemo(memoId)));
    }
}
