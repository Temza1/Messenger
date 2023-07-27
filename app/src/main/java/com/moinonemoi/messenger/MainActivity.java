package com.moinonemoi.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    public static final String LOG_TAG = "MainActivity";
    private EditText userEmail;
    private EditText userPassword;
    private Button login;
    private TextView forgotPassword;
    private TextView goToRegistration;
    private Boolean authorizedOrNot = false;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterActivity();
            }
        });

    }

    private void launchUsersActivity1() {
        Intent intent = UsersActivity.newIntent(this);
        startActivity(intent);
    }

    private void launchRegisterActivity() {
        Intent intent = RegisterActivity.newIntentRegister(this);
        startActivity(intent);
    }

    private void initViews() {
        userEmail = findViewById(R.id.editTextUserEmail);
        userPassword = findViewById(R.id.editTextUserPassword);
        login = findViewById(R.id.buttonLogin);
        forgotPassword = findViewById(R.id.textViewForgotPassword);
        goToRegistration = findViewById(R.id.textViewGoToRegistration);
    }

    private Boolean checkValidation(String email,String password) {

        boolean emptyOrNot = true;

        if (email == null || password == null) {
            emptyOrNot = false;
        }
        return emptyOrNot;
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }
}