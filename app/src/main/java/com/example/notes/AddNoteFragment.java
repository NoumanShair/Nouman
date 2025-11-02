package com.example.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notes.R;
import com.example.notes.Note;
import com.example.notes.NoteDatabase;

public class AddNoteFragment extends Fragment {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonSave;
    private NoteDatabase noteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextDescription = view.findViewById(R.id.edit_text_description);
        buttonSave = view.findViewById(R.id.button_save);

        noteDatabase = NoteDatabase.getInstance(requireContext());

        buttonSave.setOnClickListener(v -> saveNote());

        return view;
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        // Validation
        if (title.isEmpty()) {
            editTextTitle.setError("Title is required");
            editTextTitle.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            editTextDescription.setError("Description is required");
            editTextDescription.requestFocus();
            return;
        }

        // Save to database
        Note note = new Note(title, description);
        noteDatabase.noteDao().insert(note);

        Toast.makeText(requireContext(), "Note saved successfully!", Toast.LENGTH_SHORT).show();

        // Clear fields
        editTextTitle.setText("");
        editTextDescription.setText("");
    }
}