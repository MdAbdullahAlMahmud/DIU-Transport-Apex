package com.socalledengineers.diutransportapex.ui.feedback;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.socalledengineers.diutransportapex.HomeActivity;
import com.socalledengineers.diutransportapex.R;
import com.socalledengineers.diutransportapex.model.FeedbackModel;
import com.socalledengineers.diutransportapex.utils.Display;
import com.socalledengineers.diutransportapex.utils.NodeName;

import java.util.Date;

public class FeedbackActivity extends AppCompatActivity {

    private MaterialButton submitFeedbackButton;
    private EditText feedBackEDT;
    FirebaseFirestore firestore;
    private ProgressDialog dialog;
    private FirebaseAuth mAuth;

    ImageView backToActivity;
    TextView activity_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        activity_name.setText(R.string.feedback);

        backToActivity.setOnClickListener(view -> finish());
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);

        feedBackEDT = findViewById(R.id.feedBackEDT);
        submitFeedbackButton = findViewById(R.id.submitFeedbackButton);



        submitFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedBackText = feedBackEDT.getText().toString().trim();

                if (TextUtils.isEmpty(feedBackText)){
                    feedBackEDT.setError(getResources().getString(R.string.required));
                }
                addFeedback(feedBackText);

            }
        });
    }


    private void addFeedback(String text){
        String uid = mAuth.getCurrentUser().getUid().toString();

        dialog.setMessage(getString(R.string.submitting_feedback));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        String name = "DEMO Name";


        FeedbackModel model = new FeedbackModel(text,name,uid, new Timestamp(new Date()));
        firestore.collection(NodeName.FEEDBACK_NODE).add(model).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    feedBackEDT.setText("");
                    Display.successToast(FeedbackActivity.this,getString(R.string.your_feedback_added_successfully));

                    Intent intent = new Intent(FeedbackActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }else {
                    Display.successToast(FeedbackActivity.this,getString(R.string.something_wrong_try_again_later));
                    dialog.cancel();
                }
            }
        });


    }
}