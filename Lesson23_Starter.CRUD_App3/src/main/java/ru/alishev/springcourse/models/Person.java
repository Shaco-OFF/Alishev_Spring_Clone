package ru.alishev.springcourse.models;


import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */
public class Person {
    private int id;
    //Валидация полей через аннотации. Включается через зависимости в pom.xml org.hibernate.validator
    //@NotEmpty - аннотация, запрещающая пустое поле
    @NotEmpty(message = "Name should not be empty")
    //@Size - аннотация, устанавливающая количество символов поля
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    //@Min - аннотация, устанавливающая минимальное значение для числового поля
    @Min(value = 0, message = "Age should be greater then 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    //@Email - проверка на email
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email should be valid")
    private String email;

    public Person() {

    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
