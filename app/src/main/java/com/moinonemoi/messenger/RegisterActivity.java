package com.moinonemoi.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

        /*auth = FirebaseAuth.getInstance();*/

        /*auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = auth.getCurrentUser();
                        if(user == null) {
                            Log.d(LOG_TAG,"Not authorized");
                        } else {
                            Log.d(LOG_TAG,"Authorized");
                            authorizedOrNot = true;
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG_TAG,e.getMessage());
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });*/

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (authorizedOrNot && checkValidation(email,password)) {
                    launchUsersActivity(email,password);
                } else {
                    Toast.makeText(MainActivity.this,"Сначала требуется регистрация",Toast.LENGTH_SHORT).show();
                }

            }
        });*/

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editTextEmail);
                String password = getTrimmedValue(editTextPassword);
                String name = getTrimmedValue(editTextName);
                String lastName = getTrimmedValue(editTextLastName);
                int age = Integer.parseInt(getTrimmedValue(editTextHowOldAreYou));
                launchUsersActivity();
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