package com.codegym.repository;

import com.codegym.model.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {

 @PersistenceContext
 private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("from Student",Student.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public boolean add(Student student) {

        try {
            // thêm mới
            entityManager.persist(student);
            // + xoá
//            Student deleteStudent = findById(student.getId());
//            entityManager.remove(deleteStudent);
            // + update
            Student editStudent = findById(student.getId());
            // update lại editStudent thoe thuộc tính của student
            BeanUtils.copyProperties(student,editStudent);
            entityManager.merge(editStudent);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class,id);
    }
}
