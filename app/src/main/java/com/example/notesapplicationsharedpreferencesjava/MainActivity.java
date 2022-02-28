package com.example.notesapplicationsharedpreferencesjava;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_notes;
    FloatingActionButton fab_add;
    CustomDialogClass.SaveListener listener;
    ArrayList<Note> notes;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        initViews();


    }

    void loadData(){
        ArrayList<Note> noteList = PreferencesManager.getInstance(this).getObjectList("NoteList");
        if (noteList != null) {
            notes = noteList;
        } else {
            notes = new ArrayList<>();
        }
    }

    private void initViews() {
        fab_add = findViewById(R.id.fasb_add);

        fab_add.setOnClickListener(it -> {
            add();
        });

        listener = new CustomDialogClass.SaveListener() {
            @Override
            public void save(String currentDate, String newNote) {
                notes.add(0, new Note(currentDate, newNote));
                adapter.notifyItemInserted(0);
                PreferencesManager.getInstance(MainActivity.this).saveObjectList("NoteList", notes);
            }
        };

        rv_notes = findViewById(R.id.rv_notes);
        refreshAdapter();
    }

    void refreshAdapter() {
        adapter = new NoteAdapter(this, notes);
        rv_notes.setAdapter(adapter);
    }

    void add() {
        CustomDialogClass cdd = new CustomDialogClass(this, listener);
        cdd.show();
    }
}