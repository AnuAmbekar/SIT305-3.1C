package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz extends AppCompatActivity implements View.OnClickListener{

    //Set up of answer buttons and submit button
    Button ans1, ans2, ans3, submit;
    //Set up of question count and the question text views.
    TextView question, questionCount;
    //Set up of the progress bar.
    ProgressBar progressBar;

    /** We have to increment the score, the number of times the submit button has been
     clicked (to show answers and move on to next question) as well as current index.
     We also must know the answer the user has selected with chosenAnswer. We need to know
     totalQuestions and the CurrentIndex that will be used for the progress bar.**/
    int submitClicked = 0;
    int score = 0;
    int currentIndex = 0;
    int totalQuestions = QuestionAnswer.questions.length;
    String chosenAnswer = "";
    final int CurrentIndex = (int) Math.ceil(100/QuestionAnswer.questions.length);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        //Getting the username entered from MainActivity.java with intent.
        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        textView.setText("Welcome, " + name);

        //Finding views by Id for each widget that serves a purpose.
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        submit = findViewById(R.id.submit);
        questionCount = findViewById(R.id.questionCount);
        question = findViewById(R.id.question);
        progressBar = findViewById(R.id.progressBar);

        //Initialising setOnClickListener for the buttons.
        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        submit.setOnClickListener(this);

        /** Showing the question asked, how the progress bar will increment, the index of the question,
         as well as the answers you can enter for each of the questions. **/
        question.setText(QuestionAnswer.questions[currentIndex]);
        progressBar.incrementProgressBy(CurrentIndex);
        question.setText(QuestionAnswer.questions[currentIndex]);
        questionCount.setText(currentIndex + "/" + totalQuestions);
        ans1.setText(QuestionAnswer.answers[currentIndex][0]);
        ans2.setText(QuestionAnswer.answers[currentIndex][1]);
        ans3.setText(QuestionAnswer.answers[currentIndex][2]);
        //solutionsClick();
    }

    /** This onClick function controls what happens when each button gets clicked. Whatever button the user presses, the correct
     * answer will show as green and the wrong answers as red. Initially, all the buttons are blue. When the submit button is clicked on
     * once, the answers are shown. If clicked twice, the next question is shown. **/
    @Override
    public void onClick(View view) {

        //loadNextQuestion();
        Button clickedButton = (Button) view;

        if (clickedButton.getId() == submit.getId()) {
            submitClicked+=1;
            if (chosenAnswer.equals(QuestionAnswer.correctAnswers[currentIndex]) ) {
                if (ans1.getText().toString().equals(chosenAnswer)){

                    ans1.setBackgroundColor(Color.GREEN);
                    ans2.setBackgroundColor(Color.RED);
                    ans3.setBackgroundColor(Color.RED);

                    if (submitClicked==2){
                        currentIndex++;
                        submitClicked=0;
                        progressBar.incrementProgressBy(CurrentIndex);
                        ans1.setBackgroundColor(Color.BLUE);
                        ans2.setBackgroundColor(Color.BLUE);
                        ans3.setBackgroundColor(Color.BLUE);
                        submit.setBackgroundColor(Color.BLUE);
                        score++;
                    }

                }

                if (ans2.getText().toString().equals(chosenAnswer)){

                    ans1.setBackgroundColor(Color.RED);
                    ans2.setBackgroundColor(Color.GREEN);
                    ans3.setBackgroundColor(Color.RED);

                    if (submitClicked==2){
                        currentIndex++;
                        submitClicked=0;
                        progressBar.incrementProgressBy(CurrentIndex);
                        ans1.setBackgroundColor(Color.BLUE);
                        ans2.setBackgroundColor(Color.BLUE);
                        ans3.setBackgroundColor(Color.BLUE);
                        submit.setBackgroundColor(Color.BLUE);
                        score++;
                    }

                }

                if (ans3.getText().toString().equals(chosenAnswer)){

                    ans1.setBackgroundColor(Color.RED);
                    ans2.setBackgroundColor(Color.RED);
                    ans3.setBackgroundColor(Color.GREEN);
                    if (submitClicked==2){
                        currentIndex++;
                        submitClicked=0;
                        progressBar.incrementProgressBy(CurrentIndex);
                        ans1.setBackgroundColor(Color.BLUE);
                        ans2.setBackgroundColor(Color.BLUE);
                        ans3.setBackgroundColor(Color.BLUE);
                        submit.setBackgroundColor(Color.BLUE);
                        score++;
                    }

                }




            }




            if (!chosenAnswer.equals(QuestionAnswer.correctAnswers[currentIndex]) ) {

                if (ans1.getText().toString().equals(chosenAnswer)){
                    //ans1.setBackgroundColor(Color.RED);

                    if (ans2.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans2.setBackgroundColor(Color.GREEN);
                        ans3.setBackgroundColor(Color.RED);
                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }

                    else if (ans3.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans3.setBackgroundColor(Color.GREEN);
                        ans2.setBackgroundColor(Color.RED);
                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }
                }

                if (ans2.getText().toString().equals(chosenAnswer)){
                    //ans2.setBackgroundColor(Color.RED);

                    if (ans1.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans1.setBackgroundColor(Color.GREEN);
                        ans3.setBackgroundColor(Color.RED);
                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }

                    else if (ans3.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans3.setBackgroundColor(Color.GREEN);
                        ans1.setBackgroundColor(Color.RED);
                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }
                }

                if (ans3.getText().toString().equals(chosenAnswer)){
                   // ans3.setBackgroundColor(Color.RED);

                    if (ans1.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans1.setBackgroundColor(Color.GREEN);
                        ans2.setBackgroundColor(Color.RED);

                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }

                    else if (ans2.getText().toString().equals(QuestionAnswer.correctAnswers[currentIndex])) {
                        ans2.setBackgroundColor(Color.GREEN);
                        ans1.setBackgroundColor(Color.RED);

                        if (submitClicked==2){
                            currentIndex++;
                            submitClicked=0;
                            progressBar.incrementProgressBy(CurrentIndex);
                            ans1.setBackgroundColor(Color.BLUE);
                            ans2.setBackgroundColor(Color.BLUE);
                            ans3.setBackgroundColor(Color.BLUE);
                            submit.setBackgroundColor(Color.BLUE);
                        }

                    }

                }

            }

            //currentIndex++;
            loadNextQuestion();

        }




        else {
            chosenAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }
    }

    //Loads the next question until all the questions are finished. If they're all done, the function resultsClick will do its work.
    public void loadNextQuestion() {

        if (currentIndex == totalQuestions) {
            resultsClick();
            return;
        }


        //These lines of code set up the next question, buttons and the status of the progress bar.
        question.setText(QuestionAnswer.questions[currentIndex]);
        questionCount.setText(currentIndex + "/" + totalQuestions);
        ans1.setText(QuestionAnswer.answers[currentIndex][0]);
        ans2.setText(QuestionAnswer.answers[currentIndex][1]);
        ans3.setText(QuestionAnswer.answers[currentIndex][2]);

    }


    /** Shows an alert dialog to show the results. There are two buttons: restart and finish. To finish, it goes back to the page where you
     enter your username. To restart, the quiz just gets restarted without signing in. **/
    void resultsClick() {
        if (currentIndex == totalQuestions) {
            Intent intent = getIntent();
            String name = intent.getStringExtra("username");

            String result = "";

            if (score > totalQuestions*0.50) {
                result = "passed";
            }

            else{
                result = "failed";
            }

            new AlertDialog.Builder(this)
                    .setTitle("Hello, " + name + ", Result: " + result)
                    .setMessage(score + "/" + totalQuestions)
                    .setPositiveButton("Restart", (dialogInterface, i) -> restartClick())
                    .setNegativeButton("Finish", (dialogInterface, j) -> finishClick())
                    .setCancelable(true)
                    .show();
        }
    }


    //Takes the app back to the first question of the quiz.
    void restartClick() {
        currentIndex=0;
        score=0;
        progressBar.setProgress(0);
        loadNextQuestion();
    }

    //Takes the app to the page where the username is entered.
    void finishClick() {
        finish();
    }





}