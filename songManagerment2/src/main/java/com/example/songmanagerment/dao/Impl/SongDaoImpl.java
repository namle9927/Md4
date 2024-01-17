package com.example.songmanagerment.dao.Impl;

import com.example.songmanagerment.dao.ISongDao;
import com.example.songmanagerment.model.Song;
import com.example.songmanagerment.ulti.ConnectDB;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongDaoImpl implements ISongDao {
    @Override
    public List<Song> getAllSong() {
        List<Song> list = new ArrayList<>();
        // mở kết nối
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from song");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setSongName(rs.getString("song_name"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setImageUrl(rs.getString("image_url"));
                s.setVideoUrl(rs.getString("video_url"));
                s.setStatus(rs.getBoolean("status"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Song song) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call;
        try {
            if (song.getId() == null) {
                call = conn.prepareCall("INSERT INTO song (song_name, author, description, image_url, video_url, status) VALUES (?,?,?,?,?,?)");
                call.setString(1, song.getSongName());
                call.setString(2, song.getAuthor());
                call.setString(3,song.getDescription());
                call.setString(4,song.getImageUrl());
                call.setString(5,song.getVideoUrl());
                call.setBoolean(6,song.getStatus());
            }else {
                call = conn.prepareCall("UPDATE song SET song_name =?, author = ?,description = ?,image_url =?, video_url = ?,status = ? WHERE id = ?;");
                call.setString(1,song.getSongName());
                call.setString(2,song.getAuthor());
                call.setString(3,song.getDescription());
                call.setString(4,song.getImageUrl());
                call.setString(5,song.getVideoUrl());
                call.setBoolean(6,song.getStatus());
                call.setInt(7,song.getId());
            }
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }




    @Override
    public void delete(Integer id) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("DELETE FROM song where id = ?");
            call.setInt(1,id);
            call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public Song findSongById(Integer id) {
        Connection conn = ConnectDB.openConnection();
        Song s = new Song();
        try {
            CallableStatement call = conn.prepareCall("SELECT * FROM song where id = ?");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            if(rs.next()) {
                s.setId(id);
                s.setSongName(rs.getString("song_name"));
                s.setAuthor(rs.getString("author"));
                s.setDescription(rs.getString("description"));
                s.setImageUrl(rs.getString("image_url"));
                s.setVideoUrl(rs.getString("video_url"));
                s.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return s;
    }
}
