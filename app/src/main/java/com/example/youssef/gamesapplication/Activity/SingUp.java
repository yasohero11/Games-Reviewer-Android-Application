package com.example.youssef.gamesapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.example.youssef.gamesapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
        if(!TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText())){

            if(!TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(phone.getText())){
                BackendlessUser user = new BackendlessUser();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                user.setProperty("name" , name.getText().toString());
                user.setProperty("phone" , phone.getText().toString());

                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {

                    @Override
                    public void handleResponse(BackendlessUser response) {
                        setUserProperty(user);
                        Toast.makeText(SingUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        if(fault.getCode().equals("3033")){
                            Toast.makeText(SingUp.this, "There is some error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else
                Toast.makeText(this, "Please Fill The Fields", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Please Fill The Fields", Toast.LENGTH_SHORT).show();



    }
    private void setUserProperty(BackendlessUser user) {

        final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        String textName = name.getText().toString();
        SimpleDateFormat Format = new SimpleDateFormat("hh:mm a s, dd MMM yyyy", Locale.ENGLISH);
        String date=  Format.format(new Date());

        //upload image
        Backendless.Files.Android.upload(bitmap, Bitmap.CompressFormat.PNG, 30
                , textName + date + ".png"
                , "userProfilePic", new AsyncCallback<BackendlessFile>() {
                    @Override
                    public void handleResponse(BackendlessFile response) {
                        String pic = response.getFileURL();
                        user.setProperty("userImage", pic);


                        Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                            public void handleResponse(BackendlessUser user) {
                                Toast.makeText(SingUp.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(SingUp.this, login.class);
                                startActivity(in);
                            }

                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(SingUp.this,fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(SingUp.this,fault.getMessage(), Toast.LENGTH_SHORT).show();
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