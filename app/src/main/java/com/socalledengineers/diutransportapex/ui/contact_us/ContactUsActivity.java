package com.socalledengineers.diutransportapex.ui.contact_us;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.socalledengineers.diutransportapex.R;

public class ContactUsActivity extends AppCompatActivity {


    private ImageButton client1PhoneButton,client1EmailButton,client2PhoneButton,client2EmailButton;
    private String mailSubject = "";


    ImageView backToActivity;
    TextView activity_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        activity_name = findViewById(R.id.activity_name);
        backToActivity = findViewById(R.id.finish_activity);

        activity_name.setText(R.string.contact_us);

        backToActivity.setOnClickListener(view -> finish());

        init();
        mailSubject = getResources().getString(R.string.mailSubject);

        client1PhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialIntent(getResources().getString(R.string.member1Phone));
            }
        });

        client1EmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String array[] = {getResources().getString(R.string.member1Email)};
                composeEmail(array,mailSubject);
            }
        });
        client2PhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialIntent(getResources().getString(R.string.member2Phone));
            }
        });

        client2EmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String array[] = {getResources().getString(R.string.member2Email)};
                composeEmail(array,mailSubject);
            }
        });





    }

    private  void  init(){
        client1PhoneButton = findViewById(R.id.client1PhoneButton);
        client1EmailButton = findViewById(R.id.client1EmailButton);
        client2PhoneButton = findViewById(R.id.client2PhoneButton);
        client2EmailButton = findViewById(R.id.client2EmailButton);

    }
    private void dialIntent(String number){
        String dialNumber = "tel:"+number;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(dialNumber));
        startActivity(intent);


    }


    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        startActivity(intent);
    }
}