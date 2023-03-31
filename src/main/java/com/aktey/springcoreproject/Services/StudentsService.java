package com.aktey.springcoreproject.Services;

import com.aktey.springcoreproject.Models.Student;
import com.aktey.springcoreproject.Models.StudentFileWriter;
import com.google.gson.Gson;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class StudentsService implements StudentsServiceInterface {
    private static final List<Student> studentList = new ArrayList<>();

    private final String dataPath = "files/data.csv";

    public StudentsService() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Student student = new Student(line);
                studentList.add(student);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getStudentsList() {
        Gson gson = new Gson();
        return gson.toJson(studentList);
    }

    @Override
    public String getStudent(int studentIndex) {
        Gson gson = new Gson();
        Student student = studentList.stream()
                .filter(st -> st.getIndex() == studentIndex)
                .findFirst()
                .orElse(null);
        return gson.toJson(student);
    }

    @Override
    public String updateStudent(int studentIndex, Student updatedStudent) {
        Gson gson = new Gson();
        updatedStudent.setIndex(studentIndex);
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getIndex() == studentIndex) {
                studentList.set(i, updatedStudent);
                break;
            }
        }
        StudentFileWriter studentFileWriter = new StudentFileWriter(new File(dataPath));
        try {
            studentFileWriter.writeStudents(studentList);
        } catch (IOException e) {
            System.err.println("Error during writing students to file");
        }
        return gson.toJson(updatedStudent);
    }

    @Override
    public String addStudent(Student newStudent) {
        if (!isStudentValid(newStudent)) return "A Student with this index already exists";
        studentList.add(newStudent);
        StudentFileWriter studentFileWriter = new StudentFileWriter(new File(dataPath));
        try {
            studentFileWriter.writeStudents(studentList);
        } catch (IOException e) {
            System.err.println("Error during writing students to file");
        }
        Gson gson = new Gson();
        return gson.toJson(newStudent);
    }

    @Override
    public String deleteStudent(int studentIndex) {
        Optional<Student> isExisted = studentList.stream().filter(student -> student.getIndex() == studentIndex).findFirst();
        if (isExisted.isEmpty()) return "A student with studentIndex [" + studentIndex + "] doesn't exists";
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getIndex() == studentIndex) {
                studentList.remove(i);
                break;
            }
        }
        StudentFileWriter studentFileWriter = new StudentFileWriter(new File(dataPath));
        try {
            studentFileWriter.writeStudents(studentList);
        } catch (IOException e) {
            System.err.println("Error during writing students to file");
        }

        return "The student was successfully removed!";
    }

    public boolean isStudentValid(Student student) {
        if (student.getName().isEmpty() || student.getSurname().isEmpty() ||
                student.getBirthDate().isEmpty() || student.getStudy().getName().isEmpty() ||
                student.getStudy().getMode().isEmpty() || student.getEmail().isEmpty() ||
                student.getFathersName().isEmpty() || student.getMothersName().isEmpty()) {
            System.out.println("Error: All data fields must be non-empty.");
            return false;
        }
        if (student.getIndex() <= 0 || student.getIndex() > 999999) {
            System.out.println("Error: Index number must be a positive integer with up to 6 digits.");
            return false;
        }
        for (Student s : studentList) {
            if (s.getIndex() == student.getIndex()) {
                System.out.println("Error: Index number already exists.");
                return false;
            }
        }
        return true;
    }
}
