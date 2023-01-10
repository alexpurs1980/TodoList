package com.stepicjava.todolist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    //указываем тут все методы для работы с БД

    // метод возвращает список всех заметок из базы по запросу на SQL ВЫБРАТЬ ВСЕ ИЗ ТАБЛИЦЫ notes
    @Query("SELECT * FROM notes")
    // Room делает реализацию запроса, поэтому указываем конкретную коллекцию
    // List (не общий ArrayList!)
    List<Note> getNotes();

    //метод добавления заметок
    // метод OnConflictStrategy.REPLACE опциональный. Разрешает конфликты заметок с одинаковым id.
    // REPLACE по умолчанию оптимальный. Например при редактировании мы фактически можем создать
    // новую заметку с существующим id и она заменит старую заметку с этим id.
    // Вариант IGNORE проигнорирует новую заметку и не добавит ее в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Note note);

    //метод удаления заметок по id по запросу на SQL - УДАЛИТЬ ИЗ ТАБЛИЦЫ notes ЗАПИСЬ
    // С ПАРАМЕТРОМ id = ПОЛУЧАЕМОМУ ПАРАМЕТРУ id
    @Query("DELETE FROM notes WHERE id = :id")
    void remove(int id);



}
