package com.example.crudpractice.controller;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {

    // 속성
    private final WishlistService wishlistService;

    // 생성자
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // 기능
    // 위시리스트 생성 API
    @PostMapping
    public ResponseEntity<ApiResponse<WishlistCreateResponseDto>> createWishlistApi(@RequestBody WishlistCreateRequestDto requestDto) {

        WishlistCreateResponseDto responseDto = wishlistService.createWishlist(requestDto);

        ApiResponse<WishlistCreateResponseDto> apiResponse = new ApiResponse("created", 201, responseDto);

        ResponseEntity<ApiResponse<WishlistCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 위시리스트 상세 조회 API
    @GetMapping("/{wishlistId}")
    public ResponseEntity<ApiResponse<WishlistDetailResponseDto>> getWishlistDetailApi(@PathVariable("wishlistId") Long wishlistId) {

        WishlistDetailResponseDto responseDto = wishlistService.getWishlistDetail(wishlistId);

        ApiResponse<WishlistDetailResponseDto> apiResponse = new ApiResponse<>("success",200 ,responseDto);

        ResponseEntity<ApiResponse<WishlistDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 위시리스트 전체 조회 API
    @GetMapping
    public ResponseEntity<ApiResponse<WishlistAllResponseDto>> getWishlistAllApi() {

        WishlistAllResponseDto responseDto = wishlistService.getWishlistAll();

        ApiResponse<WishlistAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<WishlistAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 위시리스트 수정 API
    @PatchMapping("/{wishlistId}")
    public ResponseEntity<ApiResponse<WishlistUpdateResponseDto>> updateWishlistApi(@PathVariable("wishlistId") Long wishlistId, @RequestBody WishlistUpdateRequestDto requestDto) {

        WishlistUpdateResponseDto responseDto = wishlistService.updateWishlist(wishlistId, requestDto);

        ApiResponse<WishlistUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<WishlistUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 위시리스트 삭제 API
    @DeleteMapping("/{wishlistId}")
    public ResponseEntity<ApiResponse<WishlistDeleteResponseDto>> deleteWishlistApi(@PathVariable("wishlistId") Long wishlistId) {

        WishlistDeleteResponseDto responseDto = wishlistService.deleteWishlist(wishlistId);

        ApiResponse<WishlistDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<WishlistDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
