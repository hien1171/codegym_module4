package com.codegym.spring_data_jpa.service;



import com.codegym.spring_data_jpa.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    List<Student> search(String searchName);

    boolean add(Student student);

    Student findById(int id);
}
