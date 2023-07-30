package com.moinonemoi.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
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

    public static final String LOG_TAG = "MainActivity";
    private EditText userEmail;
    private EditText userPassword;
    private Button login;
    private TextView forgotPassword;
    private TextView goToRegistration;
    private LoginViewModel viewModel;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observeViewModel();
        setupClickListeners();
    }

    private void setupClickListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                viewModel.login(email,password);
            }
        });

        goToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterActivity();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ForgotPasswordActivity.newIntent(
                        MainActivity.this,
                        userEmail.getText().toString().trim()
                );
                startActivity(intent);
            }
        });
    }
    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null) {
                    Intent intent = UsersActivity.newIntent(MainActivity.this);
                    startActivity(intent);
                    finish();

                }
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

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
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