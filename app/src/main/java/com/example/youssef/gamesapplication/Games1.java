package com.example.youssef.gamesapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Games1 {
    public static List<Game> games;

    static String url = "http://10.0.2.2:3000/api/v1/games";
    Response.Listener<JSONObject> listener;
    RequestQueue queue;
    private Context context;

    public  List<Game> getGames() {
        return games;
    }

    public  void setGames(String name, double price) {
        int x=1;
        Toast.makeText(context, "test"+(x++), Toast.LENGTH_SHORT).show();
        games.add(new Game(name, price));
    }

    public Games1(Context context) {
        this.context = context;

        games = new ArrayList<>();
        listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        };

        queue = Volley.newRequestQueue(context);
    }

    public JsonObjectRequest updateGame(String name, String image, double price) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", name);
            jsonBody.put("image", image);
            jsonBody.put("price", price);
            url += "/5fa6bdc2a8a1cd19946059fa";
            System.out.println(url);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                    listener
                    ,

                    (VolleyError error) -> {
                        System.out.println(error.getMessage());
                    });
            return request;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static JsonObjectRequest getGamesFromHttp() {
        try {
            JSONObject jsonBody = new JSONObject();
            System.out.println(url);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, jsonBody,
                    (JSONObject response) -> {
                        System.out.println(response.toString());
                    },

                    (VolleyError error) -> {

                    });
            return request;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static class Game {
        long id;
        String name, image;
        double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Game(String name, Double price) {
            image = "game_placeholder.png";
            this.name = name;
            this.price = price;
        }


        public JsonObjectRequest updateGame(String name, String image, double price) {
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("name", name);
                jsonBody.put("image", image);
                jsonBody.put("price", price);
                url += "/" + id;
                System.out.println(url);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                        (JSONObject response) -> {
                            System.out.println(response.toString());
                        },

                        (VolleyError error) -> {

                        });
                return request;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

    }

}
