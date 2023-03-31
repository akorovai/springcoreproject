package com.aktey.springcoreproject.Services;

import com.aktey.springcoreproject.Models.Student;

public interface StudentsServiceInterface {
    String getStudentsList();

    String getStudent(int studentIndex);

    String updateStudent(int index, Student updatedStudent);

    String addStudent(Student newStudent);

    String deleteStudent(int index);
}
