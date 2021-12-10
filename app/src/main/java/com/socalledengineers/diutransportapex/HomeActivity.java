package com.socalledengineers.diutransportapex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.customstatusbar.CustomStatusBar;
import com.socalledengineers.diutransportapex.ui.contact_us.ContactUsActivity;
import com.socalledengineers.diutransportapex.ui.faq.FAQActivity;
import com.socalledengineers.diutransportapex.ui.feedback.FeedbackActivity;
import com.socalledengineers.diutransportapex.ui.homemap.HomeMapFragment;
import com.socalledengineers.diutransportapex.ui.login.LoginActivity;
import com.socalledengineers.diutransportapex.ui.profile.ProfileActivity;

public class HomeActivity extends AppCompatActivity {
    private FrameLayout frameContainer;

    private BottomNavigationView homeBottomNavigation;
    private AppCompatImageButton navMenuButton;
    private DrawerLayout home_drawer;
    private AppCompatImageButton navButton;
    private TextView navContactUs,navFeedBack,navLogoutTV,navFAQTV,navProfileTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        initLeftDrawer();
        initLeftDrawerClickListener();

        homeBottomNavigation.setSelectedItemId(R.id.homeItem);
        setUpFragment(new HomeMapFragment());
        homeBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeItem:
                        setUpFragment(new HomeMapFragment());
                        return true;
                    case R.id.requestItem:
                        setUpFragment(new HomeMapFragment());
                        return true;
                    case R.id.activityItem:
                        setUpFragment(new HomeMapFragment());
                        return true;
                    case R.id.profileItem:
                        setUpFragment(new HomeMapFragment());
                        return true;
                    default:
                        return false;
                }
            }
        });

        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setUpFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment)
                    .commit();
        }

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        homeBottomNavigation = findViewById(R.id.homeBottomNavigation);
        navMenuButton = findViewById(R.id.navButton);
        frameContainer = findViewById(R.id.frameContainer);
        home_drawer = findViewById(R.id.home_drawer);

    }
    private void initLeftDrawerClickListener() {


        navContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ContactUsActivity.class);
                startActivity(intent);
                home_drawer.closeDrawer(GravityCompat.START);
            }
        });
        navFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(intent);
                home_drawer.closeDrawer(GravityCompat.START);
            }
        });

        navFAQTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FAQActivity.class);
                startActivity(intent);
                home_drawer.closeDrawer(GravityCompat.START);
            }
        });

        navProfileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                home_drawer.closeDrawer(GravityCompat.START);
            }
        });



        navLogoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogOut();
                home_drawer.closeDrawer(GravityCompat.START);
            }
        });

    }

    private void initLeftDrawer() {
        navFeedBack=findViewById(R.id.navFeedBack);
        navContactUs=findViewById(R.id.navContactUs);
        navProfileTV=findViewById(R.id.navProfileTV);
        navFAQTV=findViewById(R.id.navFAQTV);
        navLogoutTV=findViewById(R.id.navLogoutTV);

    }
    private void userLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(getResources().getString(R.string.nav_logout));
        builder.setMessage(R.string.logout_from_the_app);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    @Override
    public void onBackPressed() {
        if (homeBottomNavigation.getSelectedItemId() != R.id.homeItem) {
            homeBottomNavigation.setSelectedItemId(R.id.homeItem);
        } else {
            super.onBackPressed();
        }
    }
}