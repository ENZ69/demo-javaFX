package com.example.demojavafx;

public class User {

    private int id;
    private String mail, password;

    public User(int id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
