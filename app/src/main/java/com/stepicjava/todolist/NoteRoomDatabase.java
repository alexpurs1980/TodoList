package com.stepicjava.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {
    //работаем с единственным экземпляром БД, поэтому применяем паттерн Синглтон
    private static NoteRoomDatabase instance = null;
    //задаем имя БД в константе
    private static final String DB_NAME = "notes.db";

    public static NoteRoomDatabase getInstance(Application application ) {
        if (instance == null) {
            // вызываем строителя БД из библиотеки Room
            instance = Room.databaseBuilder(
                    application,
                    NoteRoomDatabase.class,
                    DB_NAME).build();
        }
        return  instance;
    }

}
