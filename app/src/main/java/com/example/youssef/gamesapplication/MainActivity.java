package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUser(View view) {

    }

    public void goToGames(View view) {
        Intent in = new Intent(this , GamesDashborad.class);
        startActivity(in);
    }
}