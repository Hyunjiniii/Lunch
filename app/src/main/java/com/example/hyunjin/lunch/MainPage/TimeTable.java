package com.example.hyunjin.lunch.MainPage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hyunjin.lunch.R;
import com.example.hyunjin.lunch.TimeTablePage.PageAdapter;

public class TimeTable extends Fragment {
    private ViewPager viewPager;
    private FragmentManager fm;
    private TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_time_table, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.time_table_viewpager);
        viewPager.setAdapter(new PageAdapter(getChildFragmentManager()));
        tab = (TabLayout) view.findViewById(R.id.time_table_tab);
        tab.setupWithViewPager(viewPager);

    }

}
