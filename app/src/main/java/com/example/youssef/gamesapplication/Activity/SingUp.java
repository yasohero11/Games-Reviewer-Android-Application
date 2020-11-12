package com.example.youssef.gamesapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.youssef.gamesapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingUp extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText email, password, name, phone;
    FloatingActionButton floatingActionButton;
    CircleImageView imageView;
    private final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        floatingActionButton = findViewById(R.id.FAB_SAVE_ACCOUNT);

        imageView = findViewById(R.id.profile_image);

        name = findViewById(R.id.ET_full_name);
        phone = findViewById(R.id.ET_Phone);
        email = findViewById(R.id.ET_Email);
        password = findViewById(R.id.ET_Password);

        floatingActionButton.setOnClickListener(this);
        imageView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.FAB_SAVE_ACCOUNT:
                getUserData();
                break;
            case R.id.profile_image:
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1, "Select Picture"), PICK_IMAGE);
        }

    }

    private void getUserData() {

        name.getText().toString();
        phone.getText().toString();
        email.getText().toString();
        password.getText().toString();

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