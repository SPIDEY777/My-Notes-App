package com.example.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private Context context;
    private List<String> noteTitles;
    private List<String> noteContents;

    public NotesAdapter(Context context, List<String> noteTitles, List<String> noteContents) {
        this.context = context;
        this.noteTitles = noteTitles;
        this.noteContents = noteContents;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        String title = noteTitles.get(position);
        String content = noteContents.get(position);
        holder.noteTitle.setText(title);
        holder.noteContent.setText(content);

        // Set click listener for opening note details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, activity_detail.class);
            intent.putExtra("noteTitle", title);
            intent.putExtra("noteContent", content);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteTitles.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteContent = itemView.findViewById(R.id.noteContent);
        }
    }
}
