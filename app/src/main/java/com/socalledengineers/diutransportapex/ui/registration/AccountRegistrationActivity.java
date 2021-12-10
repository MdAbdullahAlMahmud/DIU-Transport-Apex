package com.socalledengineers.diutransportapex.ui.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.mkrlabs.customstatusbar.CustomStatusBar;
import com.socalledengineers.diutransportapex.HomeActivity;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.ui.login.LoginActivity;
import com.socalledengineers.diutransportapex.utils.Display;


public class AccountRegistrationActivity extends AppCompatActivity implements  RegistrationContract.View {

    private TextView alreadyHaveAccount;
    private MaterialButton signUpButton;
    private EditText registrationName,registrationEmail,registrationID,registrationPassword,registrationConfirmPassword;

    private RegistrationPresenter presenter;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomStatusBar.transparentStatusBarWithIcon(this,false);
        setContentView(R.layout.activity_account_registration);
        presenter = new RegistrationPresenter(this);
        init();
        setUpDialog();

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountRegistrationActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkValidity();
            }
        });
    }

    private void setUpDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.creating_account_dialoge));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    private void checkValidity() {
        String name = registrationName.getText().toString();
        String email = registrationEmail.getText().toString();
        String id = registrationID.getText().toString();
        String password = registrationPassword.getText().toString();
        String confirmpassword = registrationConfirmPassword.getText().toString();
        if(TextUtils.isEmpty(name)){
            registrationName.setError(getString(R.string.required));
            return;
        }

        if(TextUtils.isEmpty(id)){
            registrationID.setError(getString(R.string.required));
            return;
        }
        if(TextUtils.isEmpty(email)){
            registrationEmail.setError(getString(R.string.required));
            return;
        }

        if(TextUtils.isEmpty(confirmpassword)){
            registrationConfirmPassword.setError(getString(R.string.required));
            return;
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            registrationConfirmPassword.setError(getString(R.string.required));
            return;
        }

        presenter.createAccount(email,password,name,id);

    }

    private  void  init(){
        alreadyHaveAccount=findViewById(R.id.alreadyHaveAccountTV);
        signUpButton=findViewById(R.id.signUpButton);
        registrationName=findViewById(R.id.registrationName);
        registrationID=findViewById(R.id.registrationID);
        registrationEmail=findViewById(R.id.registrationEmail);
        registrationPassword=findViewById(R.id.registrationPassword);
        registrationConfirmPassword=findViewById(R.id.registrationConfirmPassword);
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void onAccountSuccess() {
        dialog.dismiss();
        Display.successToast(this,getString(R.string.account_created_successfully_toast));
        Intent intent = new Intent(AccountRegistrationActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAccountFailure(String error) {
        dialog.dismiss();
        Display.errorToast(this,error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}