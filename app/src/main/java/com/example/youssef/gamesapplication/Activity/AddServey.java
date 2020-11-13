package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.youssef.gamesapplication.R;

public class AddServey extends AppCompatActivity {
    RatingBar ratingBar;
    EditText comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_servey);
        ratingBar = findViewById(R.id.usrrateing);
        comment = findViewById(R.id.userComment);

        System.out.println(ratingBar.getRating());

    }
}