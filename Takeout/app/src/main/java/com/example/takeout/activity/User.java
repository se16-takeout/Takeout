package com.example.takeout.activity;

//李楷 2016051604109 软件工程 2016级

public class User {
    private String name;
    private String password;
    private String scrname;
    public User(String name, String password, String scrname) {
        this.name = name;
        this.password = password;
        this.scrname=scrname;
    }

    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }
    public String getPassword() {
        return password; }
    public void setPassword(String password) {
        this.password = password; }
    public String getScrname() {
        return scrname; }
    public void setScrname(String name) {
        this.scrname = scrname; }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ",scrname='" + scrname + '\''+
                '}';
    }
}

