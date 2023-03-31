package com.aktey.springcoreproject.Models;


public class Study {
    private final String name;
    private final String mode;

    public Study(String name, String mode) {
        this.name = name;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }
}