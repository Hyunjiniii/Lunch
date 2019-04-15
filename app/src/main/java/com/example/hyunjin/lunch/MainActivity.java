package com.example.hyunjin.lunch;

import android.app.ActionBar;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
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

