package com.socalledengineers.diutransportapex.ui.login;

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
import com.socalledengineers.diutransportapex.ui.registration.*;
import com.socalledengineers.diutransportapex.utils.Display;


public class LoginActivity extends AppCompatActivity implements LoginContract.View{



    private TextView dontHaveAccount;
    private EditText loginEmail,loginPass;
    private MaterialButton loginButton;
    private LoginPresenter presenter;
    private ProgressDialog dialog;
    private TextView loginForgotPasswordTV;

    private TextView languageEN,languageBN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomStatusBar.transparentStatusBarWithIcon(this,false);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        init();
        setUpDialog();

        dontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AccountRegistrationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pass = loginPass.getText().toString();

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError(getString(R.string.required));
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    loginPass.setError(getString(R.string.required));
                    return;
                }

                presenter.userLogin(email,pass);
            }
        });

        loginForgotPasswordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

       /* if (sharedPref.getUserLocalLanguage().equals("en")){
            languageBN.setBackgroundColor(getResources().getColor(R.color.white));
            languageEN.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        }else {
            languageEN.setBackgroundColor(getResources().getColor(R.color.white));
            languageBN.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        }*/

        /*languageEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setLocale(LoginActivity.this,"en");
                sharedPref.setUserLocalLanguage("en");
                languageBN.setBackgroundColor(getResources().getColor(R.color.white));
                languageEN.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                recreate();
            }
        });

        languageBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.setLocale(LoginActivity.this,"bn");
                sharedPref.setUserLocalLanguage("bn");
                languageEN.setBackgroundColor(getResources().getColor(R.color.white));
                languageBN.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                recreate();
            }
        });*/
    }


    private void setUpDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.logging));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    private  void  init()
    {
        dontHaveAccount=findViewById(R.id.dontHaveAccountTV);

        loginButton=findViewById(R.id.loginButton);
        loginEmail=findViewById(R.id.loginEmail);
        loginPass=findViewById(R.id.loginPass);
        loginForgotPasswordTV=findViewById(R.id.loginForgotPasswordTV);
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
    public void onLoginSuccess() {
        presenter.checkUserStatus();
    }

    @Override
    public void onLoginFailure(String error) {
        Display.errorToast(this,error);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void checkUserStatus(boolean status) {
        if (status){
            Display.successToast(this,getString(R.string.login_success));
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }else {
            Display.errorToast(this,getString(R.string.account_disabled));
        }
    }
}