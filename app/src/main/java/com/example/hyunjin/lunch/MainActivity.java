package com.example.hyunjin.lunch;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.Calendar.CalendarActivity;
import com.example.hyunjin.lunch.Calendar.ReturnValue;
import com.example.hyunjin.lunch.MainPage.LunchAdapter;
import com.example.hyunjin.lunch.MainPage.LunchItem;
import com.example.hyunjin.lunch.MainPage.MainViewAdapter;
import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.main_actionbar);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MainViewAdapter pagerAdapter = new MainViewAdapter(getSupportFragmentManager());
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_time_table:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bottom_lunch_table:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bottom_setting:
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

