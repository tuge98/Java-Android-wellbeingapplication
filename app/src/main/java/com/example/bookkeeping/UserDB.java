package com.example.bookkeeping;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// Database creation
@Database(entities = {User.class, Weight.class}, version = 2, exportSchema = false)
public abstract class UserDB extends RoomDatabase {

    private static UserDB instance;

    public abstract UserDao userDao();



    public static synchronized UserDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDB.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
