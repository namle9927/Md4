package com.example.b3md4.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.b3md4.dao.IPostDao;
import com.example.b3md4.model.Post;
import com.example.b3md4.service.IPostService;

import java.util.List;
@Service
public class PostServiceImpl implements IPostService {
    // tiêm phụ thuộc
    @Autowired
    private IPostDao postDao;
    @Override
    public List<Post> findAllPost() {
        return postDao.findAllPost();
    }

    @Override
    public List<Post> findAllPostByTitleOrContent(String search) {
        return postDao.findAllPostByTitleOrContent(search);
    }

    @Override
    public void addPost(Post post) {
        postDao.addPost(post);
    }

    @Override
    public void editPost(Post post) {
        postDao.editPost(post);
    }

    @Override
    public void deletePost(int postId) {
        postDao.deletePost(postId);
    }
}

