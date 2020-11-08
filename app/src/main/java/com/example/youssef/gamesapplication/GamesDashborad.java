package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youssef.gamesapplication.adaptor.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GamesDashborad extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adaptor;
    private LinearLayoutManager layoutManager;
    private List<Games.Game> gameArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_dashborad);
        fullListWithDummyData();
        recyclerView = findViewById(R.id.gamesView);
        adaptor = new Adapter(this, gameArrayList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);
        try {
            JSONObject jsonBody = new JSONObject();

            String url = "http://10.0.2.2:3000/api/v1/games";
            System.out.println(url);

            //queue.add(Games.updateGame("ahmed mohamed ahmed", "game_placeholder.png" , 500));
        } catch (Exception e) {
            Toast.makeText(this, "There Is An Error", Toast.LENGTH_LONG).show();
        }
    }

    public void addGame(View view) {
        goTo(AddGamesActivity.class);
    }

    private void fullListWithDummyData() {
        Games games = new Games(this);

        games.setGames("Apex", 1.6);
        games.setGames("Apex1", 1.5);
        games.setGames("Apex2", 1.4);
        games.setGames("Apex3", 1.3);
        games.setGames("Apex4", 1.2);
        games.setGames("Apex5", 1.1);

        gameArrayList = games.getGames();
    }

    public void goTo(Class goToClass) {
        Intent in = new Intent(this, goToClass);
        startActivity(in);

    }
}