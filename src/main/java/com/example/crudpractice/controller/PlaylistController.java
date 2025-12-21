package com.example.crudpractice.controller;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    // 속성
    private final PlaylistService playlistService;

    // 생성자
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    // 기능
    // 플레이리스트 생성 API
    @PostMapping
    public ResponseEntity<ApiResponse<PlaylistCreateResponseDto>> createPlaylistApi(@RequestBody PlaylistCreateRequestDto requestDto) {

        PlaylistCreateResponseDto responseDto = playlistService.createPlaylist(requestDto);

        ApiResponse<PlaylistCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        ResponseEntity<ApiResponse<PlaylistCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 플레이리스트 상세 조회 API
    @GetMapping("/{playlistId}")
    public ResponseEntity<ApiResponse<PlaylistGetOneResponseDto>> getOnePlaylistApi(@PathVariable("playlistId") Long playlistId) {

        PlaylistGetOneResponseDto responseDto = playlistService.getOnePlaylist(playlistId);

        ApiResponse<PlaylistGetOneResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<PlaylistGetOneResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 플레이리스트 전체 조회 API
    @GetMapping
    public ResponseEntity<ApiResponse<PlaylistGetAllResponseDto>> getAllPlaylistApi() {

        PlaylistGetAllResponseDto responseDto = playlistService.getAllPlaylist();

        ApiResponse<PlaylistGetAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<PlaylistGetAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 플레이리스트 수정 API
    @PatchMapping("/{playlistId}")
    public ResponseEntity<ApiResponse<PlaylistUpdateResponseDto>> updatePlaylistApi(@PathVariable Long playlistId, @RequestBody PlaylistUpdateRequestDto requestDto) {

        PlaylistUpdateResponseDto responseDto = playlistService.updatePlaylist(playlistId, requestDto);

        ApiResponse<PlaylistUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<PlaylistUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 플레이리스트 삭제 API
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<ApiResponse<PlaylistDeleteResponseDto>> deletePlaylistApi(@PathVariable Long playlistId) {

        PlaylistDeleteResponseDto responseDto = playlistService.deletePlaylist(playlistId);

        ApiResponse<PlaylistDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<PlaylistDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
