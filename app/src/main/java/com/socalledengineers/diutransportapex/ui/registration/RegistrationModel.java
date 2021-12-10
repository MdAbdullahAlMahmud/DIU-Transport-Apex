package com.socalledengineers.diutransportapex.ui.registration;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.socalledengineers.diutransportapex.model.Student;
import com.socalledengineers.diutransportapex.utils.NodeName;

import java.util.Date;

public class RegistrationModel implements RegistrationContract.Model{


    private FirebaseFirestore firebaseFirestore ;
    private FirebaseAuth mAuth;

    public RegistrationModel() {
        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void OnAccountCreate(OnAccountCreateListener onAccountCreateListener,String email , String pass,String name,String id) {

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String uid = task.getResult().getUser().getUid();



    //public Student(String name, String email, String id, boolean status, String uid, long account_created_at, Double lat, Double lon) {
                    Student user = new Student(name,email,id,true,uid,new Date().getTime(),0.0,0.0);
                        firebaseFirestore.collection(NodeName.USER_NODE).document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    onAccountCreateListener.onSuccessListener();
                                }else {
                                    onAccountCreateListener.onFailureListener(task.getException().getMessage());
                                }

                            }
                        });
                }else {
                    onAccountCreateListener.onFailureListener(task.getException().getMessage());

                }
            }
        });

    }
}
