package com.moinonemoi.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "email";

    private Button buttonResetPassword;
    private EditText editTextEmail;
    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initViews();
        viewModel = new ViewModelProvider(ForgotPasswordActivity.this).get(ResetPasswordViewModel.class);
        observeViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextEmail.setText(email);
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                viewModel.resetPassword(email);
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                Toast.makeText(ForgotPasswordActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getIsSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if(success) {
                    Toast.makeText(ForgotPasswordActivity.this, R.string.reset_link_send,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Intent newIntent(Context context,String email) {
        Intent intent = new Intent(context,ForgotPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL,email);
        return intent;
    }

    private void initViews() {
        buttonResetPassword = findViewById(R.id.resetPasswordButton);
        editTextEmail = findViewById(R.id.editTextYourEmail);
    }
}