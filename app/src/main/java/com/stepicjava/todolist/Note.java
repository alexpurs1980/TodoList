package com.stepicjava.todolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName =  "notes")
public class Note {
    // задаем поля класса (id - первичный ключ)
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String text;
    private int priority;

    // добавляем конструктор для инициализации всех полей


    public Note(int id, String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    @Ignore
    public Note(String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    // добавляем геттеры на все поля для возможности доступа из кода

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }
}
