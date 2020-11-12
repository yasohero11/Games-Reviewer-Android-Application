package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.rt.data.EventHandler;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.Adapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class GamesDashborad extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static Adapter adaptor;
    private LinearLayoutManager layoutManager;
    private AVLoadingIndicatorView loadingIndicatorView;
    DataQueryBuilder queryBuilder;
    List<Game> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_dashborad);


        getData();
    }

    public void addGame(View view) {
        goTo(AddGamesActivity.class);
    }


    private void setUpRecycleView() {
        recyclerView = findViewById(R.id.gamesView);
        loadingIndicatorView = findViewById(R.id.avi);
        adaptor = new Adapter(this, arrayList);
        recyclerView.setAdapter(adaptor);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void getData() {
        setUpRecycleView();

        queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(100);
        queryBuilder.setSortBy("created DESC");
        Backendless.Data.of(Game.class).find(queryBuilder, new AsyncCallback<List<Game>>() {
            @Override
            public void handleResponse(List<Game> response) {
                loadingIndicatorView.smoothToHide();

                arrayList = response;
                adaptor.setList(arrayList);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(GamesDashborad.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        EventHandler<Game> rt = Backendless.Data.of(Game.class).rt();
        rt.addCreateListener(new AsyncCallback<Game>() {
            @Override
            public void handleResponse(Game response) {
                arrayList.add(response);
                adaptor.notifyDataSetChanged();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(GamesDashborad.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goTo(Class goToClass) {
        Intent in = new Intent(this, goToClass);
        startActivity(in);

    }
}