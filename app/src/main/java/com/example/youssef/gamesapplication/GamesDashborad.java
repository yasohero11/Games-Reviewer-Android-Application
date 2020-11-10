package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.youssef.gamesapplication.adaptor.Adapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GamesDashborad extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adaptor;
    private LinearLayoutManager layoutManager;
    private Games games;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_dashborad);
        recyclerView = findViewById(R.id.gamesView);
        games = new Games(this);

        gamesInit();

    }

    public void addGame(View view) {
        goTo(AddGamesActivity.class);
    }

    private void gamesInit() {
        Games.setGames(()->{

            adaptor = new Adapter(this, Games.getGames());
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adaptor);
        });
    }

    public void goTo(Class goToClass) {
        Intent in = new Intent(this, goToClass);
        startActivity(in);

    }
}