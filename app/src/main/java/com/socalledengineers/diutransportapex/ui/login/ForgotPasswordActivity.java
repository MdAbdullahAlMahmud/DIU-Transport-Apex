package com.socalledengineers.diutransportapex.ui.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.customstatusbar.CustomStatusBar;
import com.socalledengineers.diutransportapex.utils.Display;

import com.socalledengineers.diutransportapex.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView resetEmailTV;
    private EditText forgotPasswordEmail;
    private AppCompatButton resetPasswordButton;
    private String email;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomStatusBar.transparentStatusBarWithIcon(this,false);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        inits();
        setUpDialog();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = forgotPasswordEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    forgotPasswordEmail.setError(getString(R.string.required));
                    return;
                }
                resetPassword();
            }
        });
    }
    private void setUpDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.authenticating));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }
    private void resetPassword() {
        dialog.show();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    resetEmailTV.setVisibility(View.VISIBLE);
                    Display.errorToast(ForgotPasswordActivity.this,getString(R.string.password_reset_email_sent));
                    resetEmailTV.setText(getString(R.string.check_your_email)+" "+email);
                    resetPasswordButton.setEnabled(false);
                    dialog.dismiss();
                }else {
                    dialog.dismiss();
                    Display.errorToast(ForgotPasswordActivity.this,getResources().getString(R.string.something_wrong_try_again_later));
                }
            }
        });
    }

    private void inits() {
        resetPasswordButton = findViewById(R.id.resetPasswordButton);
        forgotPasswordEmail = findViewById(R.id.forgotPasswordEmail);
        resetEmailTV = findViewById(R.id.resetEmailTV);
    }
}