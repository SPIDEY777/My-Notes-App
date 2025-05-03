package com.example.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private Button addNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addNoteButton = findViewById(R.id.addNoteButton);

        sharedPreferences = getSharedPreferences("My Notes", Context.MODE_PRIVATE);

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(this, titles, contents);
        recyclerView.setAdapter(adapter);

        // Load notes when the activity is created
        loadNotes();

        // Add note button click listener
        addNoteButton.setOnClickListener(v -> {
            // Open Add Note Activity
            startActivity(new Intent(MainActivity.this, activity_add_note.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload notes when returning to MainActivity
        loadNotes();
    }

    private void loadNotes() {
        HashSet<String> savedTitles = (HashSet<String>) sharedPreferences.getStringSet("titles", new HashSet<>());

        titles.clear();
        contents.clear();

        for (String title : savedTitles) {
            String content = sharedPreferences.getString(title, ""); // Fetch the content for each title
            titles.add(title);
            contents.add(content);
        }

        adapter.notifyDataSetChanged();
    }
}
