package com.codegym.blog.service;

import com.codegym.blog.model.Blog;
import com.codegym.blog.repository.IBlogRepository;

public interface IBlogService {
    void create(Blog blog);
    void detail(Blog blog);
    void update(Blog blog);
      boolean delete(Blog blog);
}
