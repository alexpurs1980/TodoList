package com.stepicjava.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNote;
    private NotesAdapter notesAdapter;
    //объявляем БД
    private NoteRoomDatabase noteRoomDatabase;
    // создаем хэндлер, который будет держать ссылки на потоки
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        // создаем экзепляр БД
        noteRoomDatabase = NoteRoomDatabase.getInstance(getApplication());

        //создаем адаптер и говорим ресайклеру какой адаптер ему применять
        // и как отображать элементы (через менеджер)
        notesAdapter = new NotesAdapter();
        //устанвливаем слушатель клика
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
//                //переопределяем момент клика
//                database.remove(note.getId());
//                showNotes();
            }
        });
        recyclerViewNotes.setAdapter(notesAdapter);
        showNotes();

        // для реакции на касания (свайп) создаем объект определенного класса
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target
            ) {
                //метод при перемещении объекта
                return false;
            }

            @Override
            public void onSwiped(
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    int direction
            ) {
                //метод при свайпе объекта
                //получаем позицию объекта в РВ по которой был произведен свайп
                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        noteRoomDatabase.notesDao().remove(note.getId());
                        // передаем в хэндлер сообщение для исполнения в главном потоке
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showNotes();
                            }
                        });

                    }
                });
                thread.start();




            }
        });
        // прикрепляем тачхелпер к необходимому РВ
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // фоновый поток
                List<Note> notes = noteRoomDatabase.notesDao().getNotes();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // главный поток
                        notesAdapter.setNotes(notes);
                    }
                });

            }
        });
        thread.start();


    }
}