package com.example.contactdatabase;

public class UserModel {
    long id;
    String name;
    String dateOfBirth;
    String email;

    public UserModel(long id, String name, String dateOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
}
