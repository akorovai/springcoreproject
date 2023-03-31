package com.aktey.springcoreproject.Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentFileWriter {
    private final File file;

    public StudentFileWriter(File file) {
        this.file = file;
    }

    public void writeStudents(List<Student> studentsList) throws IOException {
        FileWriter writer = new FileWriter(file, false);
        for (Student student : studentsList) {
            writer.write(student.toString() + "\n");
        }
        writer.close();
    }
}