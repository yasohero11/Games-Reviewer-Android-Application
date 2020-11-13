package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.bumptech.glide.Glide;

import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.R;
import com.example.youssef.gamesapplication.adaptor.*;
import com.example.youssef.gamesapplication.utlit.myCallBack;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;



public class EditGameActivity extends AppCompatActivity implements IPickResult {

    EditText gameName, gamePrice, gameDescription;
    ImageView gameImage;
    Bitmap bitmap;

    myCallBack myCallBack;
    String pic ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        gameName = findViewById(R.id.gameName);
        gamePrice = findViewById(R.id.gamePrice);
        gameImage = findViewById(R.id.gameImage);
        gameDescription = findViewById(R.id.gameDescription);
        gameName.setText(Adapter.lastClickedGame.getName());
        gamePrice.setText(Adapter.lastClickedGame.getPrice());
        gameDescription.setText(Adapter.lastClickedGame.getDesc());

        Glide.with(this)
                .load(Adapter.lastClickedGame.getLogo())
                .placeholder(R.drawable.game_placeholder)
                .into(gameImage);

        myCallBack = ()->{
            Adapter.lastClickedGame.setName(gameName.getText().toString());
            Adapter.lastClickedGame.setPrice(gamePrice.getText().toString());
            Adapter.lastClickedGame.setDesc(gameDescription.getText().toString());
            if(pic != null) {
                Adapter.lastClickedGame.setLogo(pic);
                System.out.println("en game Pic of");
            }

            Backendless.Data.of(Game.class).save(Adapter.lastClickedGame, new AsyncCallback<Game>() {
                @Override
                public void handleResponse(Game response) {
                    Toast.makeText(EditGameActivity.this, "Game is Updated", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(EditGameActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        };

    }


    public void showImageDialog(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            bitmap = r.getBitmap();
            gameImage.setImageBitmap(bitmap);
        }
    }

    /*

     */

    public void onSave(View view) {
        uploadGameImage(myCallBack);
    }
    public void uploadGameImage(myCallBack myCallBack){
        if(bitmap !=null){
            System.out.println("ennnansdnasn dnasdna ndnasn dnas");
            Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.PNG, 30
                    , Adapter.lastClickedGame.getObjectId() + ".png"
                    , "GameLogo", new AsyncCallback<BackendlessFile>() {
                        @Override
                        public void handleResponse(BackendlessFile response) {
                            Toast.makeText(EditGameActivity.this, "Game Image Is Uploaded", Toast.LENGTH_SHORT).show();
                            pic = response.getFileURL();
                            myCallBack.doActions();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(EditGameActivity.this, "Game Image Updated Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else
            myCallBack.doActions();
    }
}