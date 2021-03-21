package com.example.bookkeeping;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {userdetails.class}, version = 1)
public abstract class userdatabase extends RoomDatabase {
    private static final String dbName = "user";
    private static userdatabase userdatabase;

    public static synchronized userdatabase getuserDatabase(Context context) {
        if(userdatabase == null){
            userdatabase = Room.databaseBuilder(context,userdatabase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userdatabase;
    }

    public abstract dbinterface dbinterface(); {

    }
}
