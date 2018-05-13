package com.justin.sqlite_demo.model;


public class Student {
    // Labels Table and Columns names
    public static final String TABLE = "Student";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AGE = "age";

    private int student_ID;
    private String name;
    private String email;
    private int age;

    public Student() {
    }

    public Student(int student_ID, String name, String email, int age) {
        this.student_ID = student_ID;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}