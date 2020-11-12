package com.example.youssef.gamesapplication.DataModel;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.youssef.gamesapplication.utlit.myCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GamesOld {
    public static List<Game> games;
    public static Game  modifiableGame;

    public String  url = "http://10.0.2.2:3000/api/v1/games/";
    private Response.Listener<JSONObject> updateGame ;
    private Response.Listener<JSONObject> getGames ;
    private Response.ErrorListener errorResponse ;
    private RequestQueue queue ;
    private Context context;
    String newName;
    double newPrice;

    public GamesOld(Context context) {
        this.context = context;
        games = new ArrayList<>();
        queue = Volley.newRequestQueue(context);

        errorResponse = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Games Request error Cause:" + error.getCause());
                Toast.makeText(context, "Thier is somthing wrong happend",Toast.LENGTH_LONG).show();
            }
        };
    }





    public void addGame(String name, double price, String image){
        //Game game = new Game();
        //games.add(game);
    }

    public  static List<Game> getGames(){
        return games;
    }
    public static   Game getGame(int index){
        return games.get(index);
    }
    public static void  setModifiableGame(Game game){
        modifiableGame = game;
    }
    public int size(){
        return games.size();
    }

    public  void updateGame(String name, double price){
        try {
            newName = name;
            newPrice = price;
            if(updateGame == null) {
                updateGame = (response)->{
                    System.out.println(response);
                    modifiableGame.name = newName;
                    modifiableGame.price = newPrice;

                    Toast.makeText(context, "Game is updated", Toast.LENGTH_SHORT).show();
                };
            }
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("name", name);
            jsonBody.put("price", price);
            //ImageRequest imageRequest = new ImageRequest()
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url + modifiableGame.id,
                    jsonBody, updateGame, errorResponse);
            queue.add(request);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void setGames(myCallBack myCallBack){
        try {
            if(getGames == null){
                getGames = response -> {
                    JSONObject jsonObject;
                    JSONArray jsonArray = response.optJSONArray("data");
                    try {
                        for(byte i = 0; i < jsonArray.length(); i++){

                                jsonObject = jsonArray.getJSONObject(i);

                                 games.add(
                                         new Game(
                                                 jsonObject.getString("_id"),
                                                 jsonObject.getString("name"),
                                                 jsonObject.getDouble("price")
                                                 ,jsonObject.getString("image")
                                         )
                                 );
                        }

                        myCallBack.doActions();
                    } catch (JSONException e) {
                        System.out.println("games line 103 " + e.getMessage());
                    }
                };
            }
            JSONObject jsonBody = new JSONObject();
            System.out.println(url);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, jsonBody, getGames, errorResponse);
            queue.add(request);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public class Game{
        String id;
        String name, image;
        double price;
        public Game(String id,String name, double price, String image){
            this.id =  id;
            this.name =  name;
            this.price = price;
            this.image = "https://gamesapi0.herokuapp.com/uploads/" + image ;
        }


        public String getName() {
            return name;
        }
        public String getId(){
            return id;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image ="https://gamesapi0.herokuapp.com/uploads/" + image;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", image='" + image + '\'' +
                    ", price=" + price +
                    '}';
        }

        /*
        public JsonObjectRequest updateGame(String name, String image, double price){
            try {
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("name", name);
                jsonBody.put("image", image);
                jsonBody.put("price", price);
                url+="/"+id;
                System.out.println(url);
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                        (JSONObject response)-> {
                            System.out.println(response.toString());
                        },

                        (VolleyError error)->{

                        });
                return  request;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return null;
        }

     */

    }

}
