package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText gameName, gamePrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameName =  findViewById(R.id.gameName);
        gamePrice =  findViewById(R.id.gamePrice);
    }

    public void addGame(View view) {
        try {
            if(gameName.getText().toString().isEmpty() || gamePrice.getText().toString().isEmpty()){
                Toast.makeText(this, "Please Fill Out All The Fields",Toast.LENGTH_LONG).show();
                return;
            }
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name",gameName.getText().toString());
            jsonBody.put("price",Double.parseDouble(gamePrice.getText().toString()));
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://10.0.2.2:3000/api/v1/games";
            System.out.println(url);

            JsonObjectRequest  request = new JsonObjectRequest(Request.Method.POST, url, jsonBody, this, this);
            queue.add(request);
        }catch (JSONException e){
            Toast.makeText(this, "There Is An Error",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "There Is An Error",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Error ");
        System.out.println(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            Toast.makeText(this, "Game With A Name "+response.getJSONObject("data").get("name").toString() + " is been added",Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}