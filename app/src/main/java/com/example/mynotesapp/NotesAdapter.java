package com.example.mynotesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private Context context;
    private List<String> titles;
    private List<String> contents;

    public NotesAdapter(Context context, List<String> titles, List<String> contents) {
        this.context = context;
        this.titles = titles;
        this.contents = contents;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String title = titles.get(position);
        String content = contents.get(position);

        holder.titleView.setText(title);
        holder.contentView.setText(content);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, activity_detail.class);
            intent.putExtra("noteTitle", title);
            intent.putExtra("noteContent", content);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, contentView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.noteTitleView);
            contentView = itemView.findViewById(R.id.noteContentView);
        }
    }
}
