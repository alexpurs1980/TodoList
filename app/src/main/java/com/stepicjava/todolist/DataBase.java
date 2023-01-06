package com.stepicjava.todolist;

import java.util.ArrayList;
import java.util.Random;

// хранилище всех заметок
public class DataBase {

    // создаем коллекцию для размещения в ней заметок
    private ArrayList<Note> notes = new ArrayList<>();

    // добавляем статическую переменную для хранения рабочего образа БД
    public static DataBase instance = null;


    // делаем синглтон - метод создания одного экземпляра класса для всех активити
    public static DataBase getInstance(){
        // проверяем, если БД еще нет, создаем новый экземпляр,
        // если уже есть, то не трогаем БД, работаем с ней
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    // добавили конструктор и перенесли из Мэйн активити генератор заметок
    // лучше сделать его приватным, тогда единственный метод работы с БД
    // будет работа через метод синглтона getInstace
    private DataBase(){
        // сделаем временный генератор заметок (случайный)
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            //создаем экземпляр класса Ноут с генерацией случайного приоритета от 0 до 2 (не вкл. 3)
            Note note = new Note(i, "Note" + i, random.nextInt(3));
            notes.add(note);
        }
    }

    // создаем медод добавления заметки
    public void add(Note note) {
        notes.add(note);
    }

    // создаем метод удаления заметки
    public void remove(int id) {
        // проходимся по массиву и ищем нужный id
        for (int i = 0; i < notes.size(); i++) {
            // создаем элемент получая его из коллекции по индексу
            // и если его id совпадает с искомым - удаляем
            Note note = notes.get(i);
            if (note.getId() == id) {
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getNotes() {
        // вызываем новую коллекцию с экземпляром объектов старой коллекции
        // и работа с этой коллекцией не повлияет на старую!
        return new ArrayList<>(notes);
    }
}
