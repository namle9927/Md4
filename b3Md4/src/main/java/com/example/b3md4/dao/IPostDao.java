package com.example.b3md4.dao;

import com.example.b3md4.model.Post;

import java.util.List;

public interface IPostDao {
    List<Post> findAllPost();
    List<Post> findAllPostByTitleOrContent(String search);
    void addPost(Post post);
    void editPost(Post post);
    void deletePost(int postId);
}