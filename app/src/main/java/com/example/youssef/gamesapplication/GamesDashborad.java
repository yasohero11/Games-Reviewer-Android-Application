package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GamesDashborad extends AppCompatActivity{

    public void goTo(Class goToClass){
        Intent in = new Intent(this , goToClass);
        startActivity(in);

    }
    ListView listView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_dashborad);
        listView = findViewById(R.id.gamesView);
        try {
            JSONObject jsonBody = new JSONObject();

            String url = "http://10.0.2.2:3000/api/v1/games";
            System.out.println(url);

            //queue.add(Games.updateGame("ahmed mohamed ahmed", "game_placeholder.png" , 500));
        }catch (Exception e){
            Toast.makeText(this, "There Is An Error",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteGame(View view) {
    }

    public void addGame(View view) {
        goTo(AddGamesActivity.class);
    }

    public void editGame(View view) {
    }

    public void uploadImage(View view) {
    }


}