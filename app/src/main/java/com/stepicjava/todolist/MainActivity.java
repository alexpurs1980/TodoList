package com.stepicjava.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        int colorResID = 0;
        // для каждого элемента вставляем содержимое в макет в цикле for each
        for(Note note: notes){
            // добавляем макет в линеар лэйаут, преобразовывая сначала макет из xml во view
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false
            );

            // находим текстВью для установки в него текста заметки через метод ФВБИД через вью макета
            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            // устанавливаем цвет фона (по умолчанию низкий приоритет)
            switch (note.getId()) {
                case 0:
                    colorResID = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResID = android.R.color.holo_orange_light;
                    break;
                case 2:
                    colorResID = android.R.color.holo_red_light;
            }
            int color = ContextCompat.getColor(this, colorResID);
            textViewNote.setBackgroundColor(color);

            // добавляем заметку во вью в лэйаут
            linearLayoutNotes.addView(view);

        }

    }
}