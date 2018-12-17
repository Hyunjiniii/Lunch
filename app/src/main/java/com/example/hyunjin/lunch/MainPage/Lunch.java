package com.example.hyunjin.lunch.MainPage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hyunjin.lunch.MainActivity;
import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.example.hyunjin.lunch.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Lunch extends Fragment {
    public static ArrayList<LunchItem> items = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LunchAdapter adapter;
    private int year;
    private int month;
    private int maxDay;
    BapDownloadTask mProcessTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.activity_lunch, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.lunch_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new LunchAdapter(items, getContext());
        mRecyclerView.setAdapter(adapter);

        Calendar m = Calendar.getInstance();
        year = m.get(Calendar.YEAR);
        month = m.get(Calendar.MONTH);
        maxDay = m.getMaximum(Calendar.DAY_OF_MONTH);

        items.clear();

        for (int i = 1; i <= maxDay; i++) {
            mProcessTask = new Lunch.BapDownloadTask(getContext());
            mProcessTask.execute(year, month, i);
            BapTool.restoreBapDateClass mData = BapTool.restoreBapData(getContext(), year, month, i);
            LunchItem a = new LunchItem(String.valueOf(i), mData.DayOfTheWeek, mData.Lunch);
            items.add(a);
        }
        return view;

    }

    public class BapDownloadTask extends ProcessTask {
        public BapDownloadTask(Context mContext) {
            super(mContext);
        }

        @Override
        public void onPreDownload() {
        }

        @Override
        public void onUpdate(int progress) {
        }

        @Override
        public void onFinish(long result) {

        }
    }

}
