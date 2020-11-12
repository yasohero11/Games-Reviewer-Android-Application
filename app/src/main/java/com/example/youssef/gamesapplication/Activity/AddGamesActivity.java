package com.example.youssef.gamesapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddGamesActivity extends AppCompatActivity implements View.OnClickListener {
    private AVLoadingIndicatorView loadingIndicatorView;
    TextInputEditText price, description, name;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    private final int PICK_IMAGE = 1;
    Bitmap bitmap;

    public AddGamesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);

        floatingActionButton = findViewById(R.id.FAB_SAVE_GAME);
        loadingIndicatorView = findViewById(R.id.avi_Create);
        imageView = findViewById(R.id.Game_image);

        name = findViewById(R.id.ET_Name_Game);
        price = findViewById(R.id.ET_Game_price);
        description = findViewById(R.id.ET_Game_Description);

        floatingActionButton.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.FAB_SAVE_GAME:
                getUserData();
                break;
            case R.id.Game_image:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1, "Select Picture"), PICK_IMAGE);
        }
    }

    private void getUserData() {

        Game game = new Game();
        game.setName(name.getText().toString());
        game.setPrice(price.getText().toString());
        game.setDesc(description.getText().toString());

        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        uploadGame(game);
    }

    private void uploadGame(Game game) {
        if (bitmap == null) {
            Toast.makeText(AddGamesActivity.this, "please take image first", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingIndicatorView.setVisibility(View.VISIBLE);
        SimpleDateFormat Format = new SimpleDateFormat("hh:mm a s, dd MMM yyyy", Locale.ENGLISH);
        String date = Format.format(new Date());
        //upload image
        Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.PNG, 30
                , game.getName() + date + ".png"
                , "GameLogo", new AsyncCallback<BackendlessFile>() {
                    @Override
                    public void handleResponse(BackendlessFile response) {
                        String pic = response.getFileURL();
                        Toast.makeText(AddGamesActivity.this, pic, Toast.LENGTH_SHORT).show();
                        game.setLogo(pic);

                        Backendless.Data.of(Game.class).save(game, new AsyncCallback<Game>() {
                            @Override
                            public void handleResponse(Game response) {
                                loadingIndicatorView.setVisibility(View.INVISIBLE);
                                Intent intent = new Intent(AddGamesActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                loadingIndicatorView.setVisibility(View.INVISIBLE);

                                Toast.makeText(AddGamesActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        loadingIndicatorView.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddGamesActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageView.setImageURI(null);
            imageView.setImageURI(uri);
        }
    }
}