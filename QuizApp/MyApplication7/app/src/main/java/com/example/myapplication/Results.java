package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    Button restart, finish;
    TextView finalScore, congratsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        restart = findViewById(R.id.restart);
        finish = findViewById(R.id.finish);
        finalScore = findViewById(R.id.scoreFin);

        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        congratsTextView.setText("Hello, " + name + " this is your result: ");

        Intent intent2 = getIntent();
        String lastScore = intent2.getStringExtra("score");
        finalScore.setText("Score: " + lastScore + "/" + QuestionAnswer.questions.length);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restartClick();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishClick();
            }
        });
    }

    public void restartClick() {
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void finishClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}