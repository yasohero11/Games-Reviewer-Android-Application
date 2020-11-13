package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.R;

import java.util.List;
import java.util.Map;

public class dashboard extends AppCompatActivity {

    public static Game games;
    Intent in;
    SharedPreferences prefs ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LoadRelationsQueryBuilder<Map<String, Object>> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.ofMap();
        loadRelationsQueryBuilder.setRelationName( "gameSurvey" );

        Backendless.Data.of( Game.class).loadRelations("87FF4EAB-FD23-4D0D-9A0B-B8677784890F",
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Map<String, Object>>>() {
                    @Override
                    public void handleResponse(List<Map<String, Object>> response) {
                        for(Map object : response){

                            System.out.println(object.get("userName"));
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        System.out.println(fault.getMessage());
                    }
                }
        );
    }

    public void goToUser(View view) {

    }

    public void goToGames(View view) {

            in = new Intent(this, GamesDashborad.class);
            startActivity(in);

        Intent in = new Intent(this, GamesDashborad.class);
            startActivity(in);


    }
}