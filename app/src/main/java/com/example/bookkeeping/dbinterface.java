package com.example.bookkeeping;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface dbinterface {
    @Insert
    void registerUser(userdetails userdetails);

    @Query("SELECT users.userid, users.password from users where userName=(:username) and password=(:password)")
    userdetails login(String username, String password);


}
