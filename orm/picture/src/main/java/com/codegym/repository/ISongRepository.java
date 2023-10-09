package com.codegym.repository;

import com.codegym.model.Song;

import java.util.List;

public interface ISongRepository {
    List<Song> findAll();
    boolean add(Song song);
    boolean update(Song song);
    boolean delete(Song song);
    Song findByName(String name);

}
