package com.example.crudpractice.service;

import com.example.crudpractice.dto.*;
import com.example.crudpractice.entity.Playlist;
import com.example.crudpractice.repository.PlaylistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    // 속성
    private final PlaylistRepository playlistRepository;

    // 생성자
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // 기능
    // 플레이리스트 생성 로직
    @Transactional
    public PlaylistCreateResponseDto createPlaylist(PlaylistCreateRequestDto requestDto) {

        String foundName = requestDto.getName();
        String foundWriter = requestDto.getWriter();

        Playlist playlist = new Playlist(foundName, foundWriter);

        Playlist savedPlaylist = playlistRepository.save(playlist);

        Long savedPlaylistId = savedPlaylist.getId();
        String savedPlaylistName = savedPlaylist.getName();
        String savedPlaylistWriter = savedPlaylist.getWriter();

        PlaylistCreateResponseDto responseDto = new PlaylistCreateResponseDto(savedPlaylistId, savedPlaylistName, savedPlaylistWriter);

        return responseDto;
    }

    // 플레이리스트 상세 조회 로직
    @Transactional(readOnly = true)
    public PlaylistGetOneResponseDto getOnePlaylist(Long playlistId) {

        Playlist foundPlaylist = playlistRepository.findByIdAndIsDeletedFalse(playlistId)
                .orElseThrow(() -> new RuntimeException("not found music"));

        Long foundPlaylistId = foundPlaylist.getId();
        String foundPlaylistName = foundPlaylist.getName();
        String foundPlaylistWriter = foundPlaylist.getWriter();

        PlaylistGetOneResponseDto responseDto = new PlaylistGetOneResponseDto(foundPlaylistId, foundPlaylistName, foundPlaylistWriter);

        return responseDto;
    }

    // 플레이리스트 전체 조회 로직
    @Transactional(readOnly = true)
    public PlaylistGetAllResponseDto getAllPlaylist() {

        List<Playlist> foundPlaylist = playlistRepository.findByIsDeletedFalse();
        Integer count = foundPlaylist.size();

        // 내부
        List<PlaylistGetAllResponseDto.PlaylistDto> playlistDtoList = new ArrayList<>();

        for (Playlist playlist : foundPlaylist) {

            Long foundPlaylistId = playlist.getId();
            String foundPlaylistName = playlist.getName();
            String foundPlaylistWriter = playlist.getWriter();

            PlaylistGetAllResponseDto.PlaylistDto response = new PlaylistGetAllResponseDto.PlaylistDto(foundPlaylistId, foundPlaylistName, foundPlaylistWriter);
            playlistDtoList.add(response);
        }

        // 외부
        PlaylistGetAllResponseDto playlistGetAllResponseDto = new PlaylistGetAllResponseDto(count, playlistDtoList);

        return playlistGetAllResponseDto;
    }

    // 플레이리스트 수정 로직
    @Transactional
    public PlaylistUpdateResponseDto updatePlaylist(Long playlistId, PlaylistUpdateRequestDto requestDto) {

        Playlist foundPlaylist = playlistRepository.findByIdAndIsDeletedFalse(playlistId)
                .orElseThrow(() -> new RuntimeException("not found music"));

        String updatedPlaylistName = requestDto.getName();
        String updatedPlaylistWriter = requestDto.getWriter();

        Playlist updatedPlaylist = foundPlaylist.update(updatedPlaylistName, updatedPlaylistWriter);

        PlaylistUpdateResponseDto updateResponseDto = new PlaylistUpdateResponseDto(playlistId, updatedPlaylistName, updatedPlaylistWriter);

        return updateResponseDto;

    }

    // 플레이리스트 삭제 로직
    @Transactional
    public PlaylistDeleteResponseDto deletePlaylist(Long playlistId) {

        Playlist foundPlaylist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("not found music"));

        Long foundPlaylistId = foundPlaylist.getId();

        PlaylistDeleteResponseDto responseDto = new PlaylistDeleteResponseDto(foundPlaylistId);

        playlistRepository.delete(foundPlaylist);

        return responseDto;
    }
}
