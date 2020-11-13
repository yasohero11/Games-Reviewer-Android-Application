package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.DataModel.Survey;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddServey extends AppCompatActivity {
    RatingBar ratingBar;
    EditText comment;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_servey);
        ratingBar = findViewById(R.id.usrrateing);
        comment = findViewById(R.id.userComment);
        prefs = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


    }

    public void saveReview(View view) {
        Survey survey = new Survey();
        String name = prefs.getString("name", "");
        String userImage = prefs.getString("userImage", "");
        survey.setUserName(name);
        SimpleDateFormat Format = new SimpleDateFormat("hh:mm a s, dd MMM yyyy", Locale.ENGLISH);
        String date=  Format.format(new Date());
        survey.setCreatedDate(date);
        survey.setUserImage(userImage);
        survey.setReview(comment.getText().toString());
        survey.setRate((int) ratingBar.getRating());

        ArrayList<Survey> addressCollection = new ArrayList<>();


        Backendless.Data.of(Survey.class).save(survey, new AsyncCallback<Survey>() {
            @Override
            public void handleResponse(Survey response) {
                addressCollection.add( survey);
                Backendless.Data.of( Game.class ).addRelation(Adapter.lastClickedGame, "gameSurvey", addressCollection,
                        new AsyncCallback<Integer>() {
                            @Override
                            public void handleResponse(Integer response) {
                                System.out.println("asdddddddad dsssssssdd daaaaaaaaaaaaaaaaaa");

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                System.out.println(fault.getMessage());
                            }
                        }
                );
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}