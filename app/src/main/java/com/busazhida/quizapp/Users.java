package com.busazhida.quizapp;

public class Users {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private String image;
    private int age;

    public Users(String name, String image, int age) {
        this.name = name;
        this.image = image;
        this.age = age;
    }
}

