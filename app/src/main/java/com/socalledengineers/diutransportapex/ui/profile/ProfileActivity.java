package com.socalledengineers.diutransportapex.ui.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.HomeActivity;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.model.Student;
import com.socalledengineers.diutransportapex.ui.login.ForgotPasswordActivity;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private FirebaseAuth mAuth;
    private EditText myProfileNameEdt,myProfileEmailEdt;

    private String name;
    private MaterialButton profileUpdateButton;
    private ProgressDialog dialog;
    private Student user;

    private TextView myProfileChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        getUserInfo();

        profileUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = myProfileNameEdt.getText().toString();

                if (TextUtils.isEmpty(name)){
                    myProfileNameEdt.setError(getResources().getString(R.string.required));
                    return;
                }


                updateUserProfile(name);
            }
        });

        findViewById(R.id.profileBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        myProfileChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ForgotPasswordActivity.class));
            }
        });
    }
    private void updateUserProfile(String userName) {

        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle(getString(R.string.profile_updating));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.show();

        firestore.collection(NodeName.USER_NODE).document(mAuth.getCurrentUser().getUid())
                .update("name",userName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Display.successToast(ProfileActivity.this,getString(R.string.successfully_updated));

                    dialog.dismiss();
                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Display.errorToast(ProfileActivity.this,getResources().getString(R.string.something_went_wrong));
                    Log.e("Error", task.getException().getMessage());
                    dialog.dismiss();
                }
            }
        });


    }


    private void  getUserInfo(){

        String uid = mAuth.getCurrentUser().getUid();
        firestore.collection(NodeName.USER_NODE).document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    user = task.getResult().toObject(Student.class);

                    myProfileNameEdt.setText(user.getName());
                    myProfileEmailEdt.setText(user.getEmail());
                    name = user.getName();
                }
            }
        });
    }


    private  void  init(){
        firestore = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        myProfileNameEdt = findViewById(R.id.myProfileNameEdt);
        myProfileEmailEdt = findViewById(R.id.myProfileEmailEdt);
        myProfileChangePassword = findViewById(R.id.myProfileChangePassword);
        profileUpdateButton = findViewById(R.id.profileUpdateButton);
    }
}