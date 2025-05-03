package com.example.mynotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_detail extends AppCompatActivity {

    TextView noteTitleDetail , noteContentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        noteTitleDetail = findViewById(R.id.noteTitleDetail);
        noteContentDetail = findViewById(R.id.noteContentDetail);

        Intent intent =  getIntent();
        String noteTitle = intent.getStringExtra("noteTitle");
        String noteContent  = intent.getStringExtra("noteContent");


        noteTitleDetail. setText(noteTitle);
        noteContentDetail.setText(noteContent);


    }
}