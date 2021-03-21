package com.example.bookkeeping;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface dbinterface {
    @Insert
    void registerUser(userdetails userdetails);

}
