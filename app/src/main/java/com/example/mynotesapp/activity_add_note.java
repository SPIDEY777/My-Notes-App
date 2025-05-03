package com.example.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class  activity_add_note extends AppCompatActivity {

    EditText noteTitle, noteContent;
    Button saveNoteButton;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitle = findViewById(R.id.noteTitle);
        noteContent = findViewById(R.id.noteContent);
        saveNoteButton = findViewById(R.id.saveNoteButton);

        sharedPreferences = getSharedPreferences("MyNotes", Context.MODE_PRIVATE);

        saveNoteButton.setOnClickListener(v -> {
            String title = noteTitle.getText().toString().trim();
            String content = noteContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please fill in both the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(title, content);

            HashSet<String> titles = (HashSet<String>) sharedPreferences.getStringSet("titles", new HashSet<>());
            titles.add(title);
            editor.putStringSet("titles", titles);

            editor.apply();

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(activity_add_note.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
