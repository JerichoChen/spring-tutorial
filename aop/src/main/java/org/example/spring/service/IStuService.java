package org.example.spring.service;

import org.example.spring.model.Student;

public interface IStuService {

    Student getStudent(String name, Integer age);
    void hello(String name);


}
