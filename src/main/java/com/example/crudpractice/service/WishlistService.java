package com.example.crudpractice.service;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.entity.Wishlist;
import com.example.crudpractice.repository.WishlistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    // 속성
    private final WishlistRepository wishlistRepository;
    private static final Logger log = LoggerFactory.getLogger(WishlistService.class);

    // 생성자
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // 기능
    // 위시리스트 생성 로직
    @Transactional
    public WishlistCreateResponseDto createWishlist(WishlistCreateRequestDto wishlistCreateRequestDto) {

        // 1. 데이터 준비
        String name = wishlistCreateRequestDto.getName();
        String description = wishlistCreateRequestDto.getDescription();
        Long price = wishlistCreateRequestDto.getPrice();

        // 2. 위시리스트 엔티티 생성
        Wishlist wishlist = new Wishlist(name, description, price);

        // 3. 레포지터리에 저장
        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        // 4. dto에 넣어서 전달
        Long savedWishlistId = savedWishlist.getId();
        String savedWishlistName = savedWishlist.getName();
        String savedWishlistDescription = savedWishlist.getDescription();
        Long savedPrice = savedWishlist.getPrice();

        WishlistCreateResponseDto responseDto = new WishlistCreateResponseDto(savedWishlistId, savedWishlistName, savedWishlistDescription, savedPrice);

        // 반환하기
        return responseDto;
    }

    // 위시리스트 상세 조회 로직
    @Transactional(readOnly = true)
    public WishlistDetailResponseDto getWishlistDetail(Long wishlistId) {

        // 1. 위시리스트 조회하기
        Wishlist foundWish = wishlistRepository.findByIdAndIsDeletedFalse(wishlistId)
                .orElseThrow(() -> new RuntimeException("위시리스트를 찾을 수 없습니다."));

        // 2. 데이터 준비
        Long foundWishlistId = foundWish.getId();
        String foundWishlistName = foundWish.getName();
        Long foundWishlistPrice = foundWish.getPrice();

        // 3. dto에 담기
        WishlistDetailResponseDto responseDto = new WishlistDetailResponseDto(foundWishlistId, foundWishlistName, foundWishlistPrice);

        // 반환
        return responseDto;
    }

    // 위시리스트 전체 조회 로직
    @Transactional(readOnly = true)
    public WishlistAllResponseDto getWishlistAll() {

        // 1. 위시리스트 조회하기
        List<Wishlist> foundwishlist = wishlistRepository.findByIsDeletedFalse();
        int count = foundwishlist.size();;

        // 2. 내부 dto 만들기
        List<WishlistAllResponseDto.WishlistDto> wishlistDtoList = new ArrayList<>();

        for (Wishlist wishlist : foundwishlist) {

            Long foundId = wishlist.getId();
            String foundName = wishlist.getName();
            Long foundPrice = wishlist.getPrice();

            WishlistAllResponseDto.WishlistDto response = new WishlistAllResponseDto.WishlistDto(foundId, foundName, foundPrice);

            wishlistDtoList.add(response);
        }

        // 3. 외부 dto 만들기
        WishlistAllResponseDto wishlistAllResponseDto = new WishlistAllResponseDto(count, wishlistDtoList);

        // 4. 반환
        return wishlistAllResponseDto;
    }


    // 위시리스트 수정 로직
    @Transactional
    public WishlistUpdateResponseDto updateWishlist(Long wishlistId, WishlistUpdateRequestDto requestDto) {

        // 1. 위시리스트 조회하기
        Wishlist foundWishlist = wishlistRepository.findByIdAndIsDeletedFalse(wishlistId)
                .orElseThrow(() -> new RuntimeException("위시리스트를 찾을 수 없습니다."));

        // 2. 업데이트할 데이터 준비
        String updateWishlistName = requestDto.getName();
        String updateWishlistDescription = requestDto.getDescription();
        Long updateWishlistPrice = requestDto.getPrice();

        // 3. 위시리스트 수정
        Wishlist updatedWishlist = foundWishlist.update(updateWishlistName, updateWishlistDescription, updateWishlistPrice);

        // 4. dto에 담기
        String updatedWishlistName = updatedWishlist.getName();
        Long updatedWishlistPrice = updatedWishlist.getPrice();

        WishlistUpdateResponseDto responseDto = new WishlistUpdateResponseDto(updatedWishlistName, updatedWishlistPrice);

        // 5. 반환
        return responseDto;
    }

    // 위시리스트 삭제 로직
    @Transactional
    public WishlistDeleteResponseDto deleteWishlist(Long wishlistId) {

        // 1. 위시리스트 조회
        Wishlist foundWishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("위시리스트를 찾을 수 없습니다."));

        // 2. 삭제할 위시리스트 아이디 조회
        Long foundWishlistId = foundWishlist.getId();

        // 3. dto에 삭제할 위시리스트 아이디 넣기
        WishlistDeleteResponseDto responseDto = new WishlistDeleteResponseDto(foundWishlistId);

        // 4. 위시리스트 삭제
        wishlistRepository.delete(foundWishlist);

        // 5. 반환
        return responseDto;
    }
}
