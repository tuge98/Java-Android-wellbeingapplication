package com.example.bookkeeping;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

// User table and class
@Entity(tableName = "user_table", indices = {@Index(value = {"username"}, unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    private int user_id;


    private String username;


    private String hash;
    private byte[] salt;

    public User(String username) {
        this.username = username;

    }

    public int getUser_id() {
        return user_id;
    }


    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }
}

