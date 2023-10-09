package com.codegym.repository;

import com.codegym.model.Song;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> findAll() {
        TypedQuery<Song> query=entityManager.createQuery("from Song",Song.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public boolean add(Song song) {
        try{
            entityManager.persist(song);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean update(Song song) {
        try{
            Song editSong =findByName(song.getName());
            BeanUtils.copyProperties(song,editSong);
            entityManager.merge(editSong);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Song song) {
        try{
            Song deleteSong= findByName(song.getName());
            entityManager.remove(deleteSong);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Song findByName(String name) {
        return entityManager.find(Song.class,name);
    }
}
