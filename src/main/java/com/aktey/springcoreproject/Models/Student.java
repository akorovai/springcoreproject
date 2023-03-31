package com.aktey.springcoreproject.Models;


public class Student {

    private final String name;
    private final String surname;
    private final String birthDate;
    private final Study study;
    private final String email;
    private final String fathersName;
    private final String mothersName;
    private int index;

    public Student(String line) {
        String[] splitedLine = line.split(",");
        if (splitedLine.length < 9) {
            throw new IllegalArgumentException("Input string has less than 9 comma-separated values.");
        }
        name = splitedLine[0];
        surname = splitedLine[1];
        index = Integer.parseInt(splitedLine[2].substring(1));
        birthDate = splitedLine[3];
        study = new Study(splitedLine[4], splitedLine[5]);
        email = splitedLine[6];
        fathersName = splitedLine[7];
        mothersName = splitedLine[8];
    }

    @Override
    public String toString() {
        return String.format("%s,%s,s%d,%s,%s,%s,%s,%s,%s", name, surname, index, birthDate, study.getName(), study.getMode(), email, fathersName, mothersName);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Study getStudy() {
        return study;
    }

    public String getEmail() {
        return email;
    }

    public String getFathersName() {
        return fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }
}

