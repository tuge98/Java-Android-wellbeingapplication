package com.example.bookkeeping;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

// Data Access object for database
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User User);

    @Delete
    void delete (User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Insert
    void insertWeight(Weight weight);

    @Transaction
    @Query("SELECT * FROM user_table WHERE username = :username")
    List<UserWithWeights> getUserWithWeights(String username);

    @Query("SELECT user_table.username, user_table.hash, user_table.salt, user_table.user_id from user_table where username=(:username)")
    User login(String username);

    @Query("SELECT user_table.username FROM user_table WHERE username = :username")
    String registerCheck(String username);
}
