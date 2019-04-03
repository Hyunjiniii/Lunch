package com.example.hyunjin.lunch.MainPage.Lunch;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hyunjin.lunch.MainPage.Setting;
import com.example.hyunjin.lunch.MainPage.TimeTable.TimeTable;

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
