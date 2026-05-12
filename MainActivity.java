package com.example.myapplication;

import android.os.Bundle;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "notes_db"
        ).allowMainThreadQueries().build();

        Note note = new Note();
        note.title = "Hello Android";

        db.noteDao().insert(note);
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(SyncWorker.class)
                        .build();

        WorkManager.getInstance(this)
                .enqueue(request);
    }
}