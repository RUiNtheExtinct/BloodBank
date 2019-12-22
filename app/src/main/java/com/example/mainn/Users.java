package com.example.mainn;

import androidx.annotation.NonNull;

public class Users
{
    private String name, email, bg, pno, city, pwd;
    private int age, role;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getBg() {
        return bg;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPno() {
        return pno;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public Users(String email, String name, int age, String bg, String pno, String city, String pwd, int role)
    {
        this.name = name;
        this.email = email;
        this.bg = bg;
        this.pno = pno;
        this.city = city;
        this.pwd = pwd;
        this.age = age;
        this.role = role;
    }

    @NonNull
    @Override
    public String toString() {
        return ("Name : " + name + "\nAge : " + age + "\nPhone No. : " + pno + "\n Email-ID : " + email + "\nBlood Group : " + bg);
    }
}

