package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class EditGameActivity extends AppCompatActivity implements IPickResult {

    EditText gameName, gamePrice;
    ImageView gameImage;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        gameName = findViewById(R.id.gameName);
        gamePrice = findViewById(R.id.gamePrice);
        gameName.setText(Games.modifiableGame.getName());
        gamePrice.setText(String.valueOf(Games.modifiableGame.getPrice()));
        gameImage = findViewById(R.id.gameImage);
        System.out.println(Games.modifiableGame.getImage());
        /*
        Glide.with(this).load(Games.modifiableGame.getImage())
                .placeholder(R.drawable.game_placeholder)
                .into(gameImage);

         */
    }

    public void onSave(View view) {
        MainActivity.games.updateGame(gameName.getText().toString(), Double.parseDouble(gamePrice.getText().toString()));

    }

    public void showImageDialog(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            bitmap = r.getBitmap();
            gameImage.setImageBitmap(bitmap);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", imageToString(bitmap));
            } catch (JSONException e) {
                e.printStackTrace();
            }



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    "http://10.0.2.2:3000/api/v1/games/5fa6bdc2a8a1cd19946059fa/upload",
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            System.out.println(error.getMessage());
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    String imageData =  imageToString(bitmap);
                    params.put("image", imageData);
                    return params;

                }


            };
            RequestQueue requestQueue =  Volley.newRequestQueue(EditGameActivity.this);
            requestQueue.add(request);
        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }



    public String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}