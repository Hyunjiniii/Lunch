package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hyunjin.lunch.R;

import org.w3c.dom.Text;

public class TimeTable extends Fragment {
    private TextView[][] textViews = new TextView[7][5];
    private int date, time;
    private String subject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_time_table, container, false);

        int[][] btnId = {{R.id.mon1, R.id.tue1, R.id.wed1, R.id.thu1, R.id.fri1}, {R.id.mon2, R.id.tue2, R.id.wed2, R.id.thu2, R.id.fri2}, {R.id.mon3, R.id.tue3, R.id.wed3,
                R.id.thu3, R.id.fri3}, {R.id.mon4, R.id.tue4, R.id.wed4, R.id.thu4, R.id.fri4}, {R.id.mon5, R.id.tue5, R.id.wed5, R.id.thu5, R.id.fri5}, {R.id.mon6, R.id.tue6,
                R.id.wed6, R.id.thu6, R.id.fri6}, {R.id.mon7, R.id.tue7, R.id.wed7, R.id.thu7, R.id.fri7}};
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.time_table_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimeTableAdd.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 4; j++) {
                textViews[i][j] = (TextView) view.findViewById(btnId[i][j]);
            }
        }
        if (subject != null)
            textViews[time][date].setText(subject);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setData(int date, int time, String subject) {
        Log.d("setData", String.valueOf(date) + String.valueOf(time) + subject);
        this.date = date;
        this.time = time;
        this.subject = subject;
    }
}
