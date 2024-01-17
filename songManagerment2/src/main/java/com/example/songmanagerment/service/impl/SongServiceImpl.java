package com.example.songmanagerment.service.impl;

import com.example.songmanagerment.dao.ISongDao;
import com.example.songmanagerment.model.Song;
import com.example.songmanagerment.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private ISongDao songDao;
    @Override
    public List<Song> findAll() {
        return songDao.getAllSong();
    }

    @Override
    public void save(Song song) {
        songDao.save(song);
    }

    @Override
    public void delete(Integer id) {
songDao.delete(id);
    }

    @Override
    public Song findSongById(Integer id) {
        return songDao.findSongById(id);
    }
}
