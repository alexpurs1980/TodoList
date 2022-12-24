package com.stepicjava.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutNotes;
    private FloatingActionButton buttonAddNote;

    // создаем коллекцию для размещения в ней заметок
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        // сделаем временный генератор заметок (случайный)
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            //создаем экземпляр класса Ноут с генерацией случайного приоритета от 0 до 2 (не вкл. 3)
            Note note = new Note(i, "Note" + i, random.nextInt(3));
            notes.add(note);
        }

        showNotes();

    }

    private void initViews(){
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
    // метод для показа заметок
    private void showNotes(){
        // для каждого элемента вставляем содержимое в макет в цикле for each
        for(Note note: notes){
            // добавляем макет в линеар лэйаут, преобразовывая сначала макет из xml во view
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false
            );
            linearLayoutNotes.addView(view);

        }

    }
}