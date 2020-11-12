package com.example.youssef.gamesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class userProfile extends AppCompatActivity implements IPickResult {
    EditText newName, newPassword;
    ImageView userImage;
    int toggle1 = 0, toggle2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        newName =  findViewById(R.id.newNameField);
        newPassword =  findViewById(R.id.newPasswordField);
        userImage =  findViewById(R.id.userImage);
    }

    public void showPasswordField(View view) {
        if(toggle1 % 2 == 0)
            newPassword.setVisibility(View.VISIBLE);
        else
            newPassword.setVisibility(View.INVISIBLE);

        toggle1++;
    }



    public void showEditField(View view) {
        if(toggle2 % 2 == 0)
            newName.setVisibility(View.VISIBLE);
        else
            newName.setVisibility(View.INVISIBLE);

        toggle2++;
    }

    public void showImagePicker(View view) {
        PickImageDialog.build(new PickSetup()).show(this);
    }




    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            userImage.setImageBitmap(r.getBitmap());
        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}