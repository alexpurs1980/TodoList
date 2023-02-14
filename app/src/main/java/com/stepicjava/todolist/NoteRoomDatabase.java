package com.stepicjava.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
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
                    DB_NAME)
                    .allowMainThreadQueries()
                    .build();
            // метод .allowMainThreadQueries() можно разрешать только в целях ТЕСТИРОВАНИЯ!
            // В боевом режиме БД надо запускать в отдельном потоке!
        }
        return  instance;
    }

    // пишем абстрактный метод генерации интерфеса
    public abstract NotesDao notesDao();

}
