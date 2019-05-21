package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                final int row = i;
                final int column = j;
                textViews[row][column].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (textViews[row][column].getText().length() != 0) {
                            CustomDialog customDialog = new CustomDialog(getContext(), textViews[row][column].getText().toString(), textViews[row][column].getBackground());
                            customDialog.callFunction(textViews[row][column], row, column);
                        } else {
                            CustomDialog customDialog = new CustomDialog(getContext());
                            customDialog.callFunction(textViews[row][column], row, column);
                        }

                    }
                });
            }
        }

        getPreferences();

        return view;
    }

    private void getPreferences() {
        for (int i = 0; i <= 4; i++) {
            SharedPreferences pref = getContext().getSharedPreferences(Integer.toString(i) + "요일", Context.MODE_PRIVATE);
            for (int j = 0; j <= 6; j++) {
                String textName = pref.getString(j + "교시_text", null);
                if (textName != null)
                    textViews[j][i].setText(textName);

                int backgroundColor = pref.getInt(j + "교시_color", 0);
                if (backgroundColor != 0)
                    textViews[j][i].setBackgroundColor(backgroundColor);
            }
        }
    }
}
