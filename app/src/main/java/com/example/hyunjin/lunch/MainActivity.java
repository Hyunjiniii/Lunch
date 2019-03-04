package com.example.hyunjin.lunch;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hyunjin.lunch.MainPage.MainViewAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ImageButton edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.time_table_actionbar);
        setContentView(R.layout.activity_main);

        edit_btn = (ImageButton) findViewById(R.id.time_table_edit_btn);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        MainViewAdapter pagerAdapter = new MainViewAdapter(getSupportFragmentManager());
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_time_table:
                        edit_btn.setVisibility(View.VISIBLE);
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bottom_lunch_table:
                        edit_btn.setVisibility(View.GONE);
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bottom_setting:
                        edit_btn.setVisibility(View.GONE);
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        viewPager.setAdapter(pagerAdapter);

    }
}

