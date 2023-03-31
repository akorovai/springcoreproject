package com.aktey.springcoreproject.RestControllers;

import com.aktey.springcoreproject.Models.Student;
import com.aktey.springcoreproject.Services.StudentsServiceInterface;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentsServiceInterface studentsRepositoryInterface;

    @Autowired
    public StudentController(StudentsServiceInterface studentsRepositoryInterface) {
        this.studentsRepositoryInterface = studentsRepositoryInterface;
    }

    @GetMapping
    public String getStudents() {
        return studentsRepositoryInterface.getStudentsList();
    }

    @GetMapping("/{index}")
    public String getStudentByIndex(@PathVariable String index) {
        return studentsRepositoryInterface.getStudent(Integer.parseInt(index.substring(1)));
    }

    @PutMapping("/s{index}")
    public String updateStudent(@PathVariable int index, @RequestBody String json) {
        Gson gson = new Gson();
        Student updatedStudent = gson.fromJson(json, Student.class);
        return studentsRepositoryInterface.updateStudent(index, updatedStudent);
    }

    @PostMapping
    public String addNewStudent(@RequestBody String newJson) {
        Gson gson = new Gson();
        Student newStudent = gson.fromJson(newJson, Student.class);
        return studentsRepositoryInterface.addStudent(newStudent);
    }

    @DeleteMapping("s{index}")
    public String deleteStudent(@PathVariable int index) {
        return studentsRepositoryInterface.deleteStudent(index);
    }
}
