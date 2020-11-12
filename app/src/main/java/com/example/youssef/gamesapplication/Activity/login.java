package com.example.youssef.gamesapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.dx.dxloadingbutton.lib.LoadingButton;
import com.example.youssef.gamesapplication.R;

public class login extends AppCompatActivity {
    private Button singUp;
    LoadingButton login;
    private EditText email, password;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Backendless.initApp(this, "8BCABE25-2DB1-59F4-FF1C-E50472554900", "CAC3D562-09DF-45F5-BCBF-092557DA4280");

        login = findViewById(R.id.login);
        singUp = findViewById(R.id.singUp);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    public void sendToSingUp(View view) {
        Intent intent = new Intent(this, SingUp.class);
        startActivity(intent);
    }

    public void login(View view) {
        login.startLoading();
        Backendless.UserService.login(email.getText().toString(), password.getText().toString(), new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser user) {
                Backendless.UserService.setCurrentUser(user);
                login.loadingSuccessful();
                login.reset();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                sendToMain();
            }

            public void handleFault(BackendlessFault fault) {
                login.loadingFailed();
                Toast.makeText(login.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}