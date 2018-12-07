package com.example.hyunjin.lunch.MainPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Switch;

public class MainViewAdapter extends FragmentStatePagerAdapter {

    public MainViewAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TimeTable tab1 = new TimeTable();
                return tab1;
            case 1:
                Lunch tab2 = new Lunch();
                return tab2;
            case 2:
                Setting tab3 = new Setting();
                return tab3;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
