package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditGameActivity extends AppCompatActivity {
    Games.Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        game =  Games.getGame(getIntent().getExtras().getInt("gamePosition"));
        System.out.println(game);

    }
}