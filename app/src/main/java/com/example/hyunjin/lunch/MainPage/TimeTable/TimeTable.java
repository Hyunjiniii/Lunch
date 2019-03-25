package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.R;

public class TimeTable extends Fragment {
    private TextView[][] textViews = new TextView[7][5];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_time_table, container, false);

        int[][] btnId = {{R.id.mon1, R.id.tue1, R.id.wed1, R.id.thu1, R.id.fri1}, {R.id.mon2, R.id.tue2, R.id.wed2, R.id.thu2, R.id.fri2}, {R.id.mon3, R.id.tue3, R.id.wed3,
                R.id.thu3, R.id.fri3}, {R.id.mon4, R.id.tue4, R.id.wed4, R.id.thu4, R.id.fri4}, {R.id.mon5, R.id.tue5, R.id.wed5, R.id.thu5, R.id.fri5}, {R.id.mon6, R.id.tue6,
                R.id.wed6, R.id.thu6, R.id.fri6}, {R.id.mon7, R.id.tue7, R.id.wed7, R.id.thu7, R.id.fri7}};
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 4; j++) {
                textViews[i][j] = (TextView) view.findViewById(btnId[i][j]);
            }
        }

        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 4; j++) {
                final int finalI = i;
                final int finalJ = j;
                textViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CustomDialog customDialog = new CustomDialog(getContext());
                        customDialog.callFunction(textViews[finalI][finalJ], finalI, finalJ);
                        Toast.makeText(getContext(), String.valueOf(finalI) + String.valueOf(finalJ), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }
}
