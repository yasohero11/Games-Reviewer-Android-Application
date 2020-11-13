package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.Adapter;
import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs ;
    private RecyclerView recyclerView;
    public static Adapter adaptor;
    public static final String MyPREFERENCES = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String password =  prefs.getString("password", null);
        String role = prefs.getString("role", null);
        System.out.println(email + " " + password+ " "+ role);

        if (email == null || password == null){
            Intent intent = new Intent(this , login.class);
            startActivity(intent);
        }

        recyclerView = findViewById(R.id.gameViewer);


    }

    public void logout(View view) {
        System.out.println("logout .. .");
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", "");
        editor.putString("password", "");
        editor.putString("role", "");
        editor.apply();
        Intent in = new Intent(this, login.class);
        startActivity(in);
    }
}