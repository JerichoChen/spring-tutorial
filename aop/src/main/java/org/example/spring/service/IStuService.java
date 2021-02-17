package org.example.spring.service;

import org.example.spring.model.Student;

public interface IStuService {

    Student getStudent(String name, Integer age);

    void hello(String name);

    Student getStudentAfterReturning(String name, Integer age);

    Student getStudentAfterThrowing(String name, Integer age);

    Student getStudentAround(String name, Integer age);

}
