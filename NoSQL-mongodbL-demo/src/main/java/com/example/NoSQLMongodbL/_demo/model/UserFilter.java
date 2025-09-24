package com.example.NoSQLMongodbL._demo.model;


public class UserFilter {
    private String name;
    private Integer age;

    public UserFilter(String name, Integer age) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
