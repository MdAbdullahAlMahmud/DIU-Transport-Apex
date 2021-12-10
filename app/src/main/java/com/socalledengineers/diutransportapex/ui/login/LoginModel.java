package com.socalledengineers.diutransportapex.ui.login;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.model.Student;
import com.socalledengineers.diutransportapex.utils.NodeName;


public class LoginModel implements LoginContract.Model {
    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;
    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void OnUserLogin(OnUserLoginListener userLoginListener, String email, String pass) {

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userLoginListener.onSuccessListener();

                }else {
                    userLoginListener.onFailureListener(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void onUserStatus(OnUserLoginListener userLoginListener) {
        firebaseFirestore.collection(NodeName.USER_NODE).document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    Student user = task.getResult().toObject(Student.class);
                    userLoginListener.onUserStatus(user.isStatus());
                }else {
                    userLoginListener.onFailureListener("banned");
                }
            }
        });
    }


}
