package com.stepicjava.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;
    private NotesAdapter notesAdapter;

    // получаем образец класса из синглтона
    private DataBase database = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        //создаем адаптер и говорим ресайклеру какой адаптер ему применять
        // и как отображать элементы (через менеджер)
        notesAdapter = new NotesAdapter();
        recyclerViewNotes.setAdapter(notesAdapter);
        // программный способ внизу установки параметров отображения. А можно через макет
        // и параметр app:layoutManager
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));

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
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }
    // метод для показа заметок (тут делаем Ресайклер вью!)
    private void showNotes(){
        //включаем ресайклер с адаптером
        notesAdapter.setNotes(database.getNotes());

    }
}