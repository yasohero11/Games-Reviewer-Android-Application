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

import com.example.youssef.gamesapplication.DataModel.Game;
import com.example.youssef.gamesapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddGamesActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText price, description, name;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    private final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_games);

        floatingActionButton = findViewById(R.id.FAB_SAVE_GAME);

        imageView = findViewById(R.id.Game_image);

        name = findViewById(R.id.ET_Name_Game);
        price = findViewById(R.id.ET_Game_price);
        description = findViewById(R.id.ET_Game_Description);

        floatingActionButton.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
        

        final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

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