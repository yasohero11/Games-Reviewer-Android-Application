package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.youssef.gamesapplication.DataModel.GamesOld;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.Adapter;

public class GamesDashborad extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static Adapter adaptor;
    private LinearLayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_dashborad);
        recyclerView = findViewById(R.id.gamesView);

        adaptor = new Adapter(this, GamesOld.getGames());
        recyclerView.setAdapter(adaptor);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void addGame(View view) {
        goTo(AddGamesActivity.class);
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(adaptor!=null) {
            adaptor.notifyDataSetChanged();

        }
    }

    public void goTo(Class goToClass) {
        Intent in = new Intent(this, goToClass);
        startActivity(in);

    }
}