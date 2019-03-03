package com.example.hyunjin.lunch.TimeTablePage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

public class PageAdapter extends FragmentPagerAdapter {
    private static int PAGE_NUMBER = 5;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return Monday.newInstance();
            case 1:
                return Tuesday.newInstance();
            case 2:
                return Wednesday.newInstance();
            case 3:
                return Thursday.newInstance();
            case 4:
                return Friday.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "월요일";
            case 1:
                return "화요일";
            case 2:
                return "수요일";
            case 3:
                return "목요일";
            case 4:
                return "금요일";
            default:
                return null;
        }
    }
}
