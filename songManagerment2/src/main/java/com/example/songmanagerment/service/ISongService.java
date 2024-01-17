package com.example.songmanagerment.service;

import com.example.songmanagerment.model.Song;

import java.util.List;
public interface ISongService {
    public List<Song> findAll();
    void save(Song song);
    void delete(Integer id);
    Song findSongById(Integer id);
}
