package com.moinonemoi.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    public Button ButtonSignUp;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextHowOldAreYou;
    private RegistrationViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();


        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editTextEmail);
                String password = getTrimmedValue(editTextPassword);
                String name = getTrimmedValue(editTextName);
                String lastName = getTrimmedValue(editTextLastName);
                int age = Integer.parseInt(getTrimmedValue(editTextHowOldAreYou));
                viewModel.signUp(email,password,name,lastName,age);
            }
        });

    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if(errorMessage != null) {
                    Toast.makeText(RegisterActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null) {
                    Intent intent = UsersActivity.newIntent(RegisterActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void launchUsersActivity() {
        Intent intent = UsersActivity.newIntent(this);
        startActivity(intent);
    }

    public static Intent newIntentRegister(Context context) {
        Intent intent = new Intent(context,RegisterActivity.class);
        return intent;
    }

    private void initViews() {
        ButtonSignUp = findViewById(R.id.buttonSignUp);
        editTextEmail = findViewById(R.id.editTextYourEmailRegister);
        editTextPassword = findViewById(R.id.editTextPasswordRegister);
        editTextName = findViewById(R.id.edittextYourNameRegister);
        editTextLastName = findViewById(R.id.edittextYourLastNameRegister);
        editTextHowOldAreYou = findViewById(R.id.edittextYearRegister);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

}