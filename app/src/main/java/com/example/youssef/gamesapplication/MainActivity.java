package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.youssef.gamesapplication.adaptor.Adapter;

public class MainActivity extends AppCompatActivity {
    public static Games games;
    Intent in;
    boolean isDataLoaded =  false;


    private void gamesInit() {
        games.setGames(()->{
            isDataLoaded =  true;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        games = new Games(this);
        gamesInit();
    }

    public void goToUser(View view) {

    }

    public void goToGames(View view) {
        if(isDataLoaded) {
            in = new Intent(this, GamesDashborad.class);
            startActivity(in);
        }
        else
            Toast.makeText(this, "Data is loadeing" , Toast.LENGTH_SHORT).show();

    }
}