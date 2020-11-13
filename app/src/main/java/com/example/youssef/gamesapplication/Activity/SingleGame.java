package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.bumptech.glide.Glide;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.DataModel.Survey;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SingleGame extends AppCompatActivity {

    ImageView imageView;
    TextView reviewdGameName, reviewdGameName2, reviewedGamePrice, reviewdGameDescription;
    RecyclerView surveyRc;
    SurevyAdapter surevyAdapter;
    RecyclerView.LayoutManager layoutManager;
    private List<Survey> surveys = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_gmae);
        imageView = findViewById(R.id.gameImage);
        reviewdGameName = findViewById(R.id.reviewdGameName);
        reviewdGameName2 = findViewById(R.id.reviewdGameName2);
        reviewedGamePrice = findViewById(R.id.reviewedGamePrice);
        reviewdGameDescription = findViewById(R.id.reviewdGameDescription);
        surveyRc = findViewById(R.id.surveyRc);
        layoutManager = new LinearLayoutManager(this);
        surevyAdapter = new SurevyAdapter(this, surveys);
        surveyRc.setAdapter(surevyAdapter);
        surveyRc.setLayoutManager(layoutManager);

        Glide.with(this)
                .load(Adapter.lastClickedGame.getLogo())
                .placeholder(R.drawable.game_placeholder)
                .into(imageView);

        reviewdGameName.setText(Adapter.lastClickedGame.getName());
        reviewdGameName2.setText(Adapter.lastClickedGame.getName());
        reviewedGamePrice.setText(Adapter.lastClickedGame.getPrice() +"$");
        reviewdGameDescription.setText(Adapter.lastClickedGame.getDesc());

        LoadRelationsQueryBuilder<Map<String, Object>> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.ofMap();
        loadRelationsQueryBuilder.setRelationName( "gameSurvey" );



        Backendless.Data.of( Game.class).loadRelations(Adapter.lastClickedGame.getObjectId(),
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Map<String, Object>>>() {
                    @Override
                    public void handleResponse(List<Map<String, Object>> response) {

                        for(Map object : response){


                            Survey survey = new Survey();
                            survey.setUserName(object.get("userName").toString());
                            survey.setUserImage(object.get("userImage").toString());
                            survey.setRate((int)object.get("rate"));
                            survey.setReview(object.get("review").toString());
                            survey.setCreatedDate(object.get("createdDate").toString());
                            surveys.add(survey);
                        }

                        surevyAdapter.setList(surveys);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        System.out.println("enn");
                    }


                }
        );


    }

    public void goToAddSurveyActivity(View view) {
        Intent in = new Intent(this , AddServey.class);
        startActivity(in);

    }
}