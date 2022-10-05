package ru.alishev.springcourse.models;

public class Person {
    private int id;
    private String name;
    private String surname;
    private int age;
    public Person parent;
    private String status;

    public Person() {
    }
    public Person(String status) {
        this.status = status;
    }

    public Person(int id, String name, String surname, int age, Person parent) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if (getStatus() != null) {
            return status;
        }else {
            return name;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


