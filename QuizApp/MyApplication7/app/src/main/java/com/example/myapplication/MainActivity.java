package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    SharedPreferences sharedPreferences;
    String USER_NAME;
    CheckBox checkbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Using findViewById for all the widgets that will be necessary in the xml.
        button = findViewById(R.id.b1);
        editText = findViewById(R.id.editText);
        checkbox = findViewById(R.id.checkbox);

        //using shared preferences to ensure that the username gets remembered when the checkbox is ticked.
        sharedPreferences = getSharedPreferences("com.example.sharedpreferenceslogin", MODE_PRIVATE);
        checkSharedPreferences();

    }

    //Function jumpClick gets us to the Quiz.java activity and then remembers the name entered if the checkbox is ticked.
    public void jumpClick(View view) {
        Intent intent = new Intent(this, Quiz.class);
        intent.putExtra("username", editText.getText().toString());
        startActivity(intent);

        if (checkbox.isChecked()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USER_NAME, editText.getText().toString());
            editor.apply();
        }
        else {
            sharedPreferences.edit().putString(USER_NAME, "").apply();
        }

    }

    public void checkSharedPreferences() {
        String username = sharedPreferences.getString(USER_NAME, "");
        editText.setText(username);
    }


}