package com.example.songmanagerment.dao;

import com.example.songmanagerment.model.Song;

import java.util.List;

public interface ISongDao {
    public List<Song> getAllSong();
    void save(Song song);
    void delete(Integer id);
    Song findSongById(Integer id);
}
