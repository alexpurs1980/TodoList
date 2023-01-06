package com.stepicjava.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    //наследуемся от адаптера и переопределяем методы
    // в самом адаптере надо держать коллекцию отображаемых объектов, с параметрами

    private ArrayList<Note> notes = new ArrayList<>();

    // добавляем сеттер для изменения параметров объекта снаружи (вставка новой коллекции)


    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        // если данные изменились - сообщаем адаптеру
        notifyDataSetChanged();
    }

    // метод для созданию вью показываем адаптеру в методе onCreateViewHolder
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false
        );
        return new NotesViewHolder(view);
    }

    // метод для подсказке адаптеру какие параметры вью при прокрутке, цвета и проччее
    @Override
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());

        // устанавливаем цвет фона
        int colorResId = 0;
        switch (note.getPriority()) {
            case 0:
                colorResId = android.R.color.holo_green_light;
                break;
            case 1:
                colorResId = android.R.color.holo_orange_light;
                break;
            case 2:
                colorResId = android.R.color.holo_red_light;
        }
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);
        viewHolder.textViewNote.setBackgroundColor(color);

    }

    @Override
    public int getItemCount() {
        // возвращает количество объектов, которые находятся в коллекции
        return notes.size();
    }

    // добавляем класс вьюХолдер, который будет содержать объекты для добавления внутри себя
    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNote;

        // переопределяем конструктор, который в качестве параметра принимает вью,
        // который мы создаем из макета
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);
        }
    }
}
