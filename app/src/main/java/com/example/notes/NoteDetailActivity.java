package com.example.notes;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        textViewTitle = findViewById(R.id.text_view_detail_title);
        textViewDescription = findViewById(R.id.text_view_detail_description);

        // Get data from intent
        String title = getIntent().getStringExtra("NOTE_TITLE");
        String description = getIntent().getStringExtra("NOTE_DESCRIPTION");

        textViewTitle.setText(title);
        textViewDescription.setText(description);

        // Enable back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Note Details");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
