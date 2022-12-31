package com.stepicjava.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
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

    // получаем образец класса из синглтона
    private DataBase database = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
    // перенесли показ заметок в метод онРезюм что бы заметки отображались не только
    // при создании активити, а каждый раз при обновлении (например при возвращении)
    @Override
    protected void onPostResume() {
        super.onPostResume();
        showNotes();
    }

    private void initViews(){
        linearLayoutNotes = findViewById(R.id.linearLayoutNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
    // метод для показа заметок
    private void showNotes(){
        // очищаем лэйаут каждый раз при обновлении вьюшек
        linearLayoutNotes.removeAllViews();
        int colorResID = 0;
        // для каждого элемента вставляем содержимое в макет в цикле for each
        for(Note note: database.getNotes()){
            // добавляем макет в линеар лэйаут, преобразовывая сначала макет из xml во view
            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutNotes,
                    false
            );

            // делаем удаление вью по клику
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //вызываем метод ремув у базы данных по айди заметки
                    database.remove(note.getId());
                    showNotes();
                }
            });

            // находим текстВью для установки в него текста заметки через метод ФВБИД через вью макета
            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            // устанавливаем цвет фона
            switch (note.getPriority()) {
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