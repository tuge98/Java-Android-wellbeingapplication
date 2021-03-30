package com.example.bookkeeping;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
//Defining Dao to offer abstract access to database
@Dao
public interface dbinterface {
    @Insert
    void registerUser(userdetails userdetails);


    @Query("SELECT users.username, users.password, users.userid from users where userName=(:username)")
    userdetails login(String username);

    @Query("DELETE FROM users")
    void deleteAll();


}
