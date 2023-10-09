package com.codegym.blog.service;

import com.codegym.blog.model.Blog;
import com.codegym.blog.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogService implements IBlogService{
    @Autowired
    private IBlogRepository blogRepository;



    @Override
    public void create(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void detail(Blog blog) {

    }

    @Override
    public void update(Blog blog) {
          blogRepository.save(blog);
    }

    @Override
    public boolean delete(Blog blog) {
        try {
            blogRepository.delete(blog);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
