package com.stepicjava.todolist;

public class Note {
    // задаем поля класса
    private int id;
    private String text;
    private int priority;

    // добавляем конструктор для инициализации всех полей


    public Note(int id, String text, int priority) {
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
