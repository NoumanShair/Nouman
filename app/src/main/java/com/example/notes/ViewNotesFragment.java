package com.example.notes;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.NoteDetailActivity;
import com.example.notes.R;
import com.example.notes.NotesAdapter;
import com.example.notes.Note;
import com.example.notes.NoteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewNotesFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NoteDatabase noteDatabase;
    private List<Note> notesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_notes, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        noteDatabase = NoteDatabase.getInstance(requireContext());
        notesList = new ArrayList<>();

        notesAdapter = new NotesAdapter(notesList, note -> {
            // Handle note click
            Intent intent = new Intent(requireContext(), NoteDetailActivity.class);
            intent.putExtra("NOTE_ID", note.getId());
            intent.putExtra("NOTE_TITLE", note.getTitle());
            intent.putExtra("NOTE_DESCRIPTION", note.getDescription());
            startActivity(intent);
        });

        recyclerView.setAdapter(notesAdapter);

        loadNotes();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        notesList.clear();
        notesList.addAll(noteDatabase.noteDao().getAllNotes());
        notesAdapter.notifyDataSetChanged();
    }
}