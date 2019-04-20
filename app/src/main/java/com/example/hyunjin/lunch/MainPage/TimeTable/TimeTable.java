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
        final TextView null_txt = (TextView) view.findViewById(R.id.time_table_null_txt);
        SharedPreferences pref = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (!pref.getBoolean("NullText", true))
            null_txt.setVisibility(View.GONE);

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
                        if (textViews[finalI][finalJ].getText().length() != 0) {
                            CustomDialog customDialog = new CustomDialog(getContext(), textViews[finalI][finalJ].getText().toString(), textViews[finalI][finalJ].getBackground());
                            customDialog.callFunction(null_txt, textViews[finalI][finalJ], finalI, finalJ);
                        } else {
                            CustomDialog customDialog = new CustomDialog(getContext());
                            customDialog.callFunction(null_txt, textViews[finalI][finalJ], finalI, finalJ);
                        }

                    }
                });
            }
        }

        getPreferences("mon", 0);
        getPreferences("tue", 1);
        getPreferences("wed", 2);
        getPreferences("thu", 3);
        getPreferences("fri", 4);

        return view;
    }

    private void getPreferences(String date, int index) {
        SharedPreferences pref = getContext().getSharedPreferences(date, Context.MODE_PRIVATE);
        for (int i = 0; i <= 6; i++) {
            if (pref.getString(String.valueOf(i), null) != null)
                textViews[i][index].setText(pref.getString(String.valueOf(i), null));
            if (pref.getInt(String.valueOf(i), 0) != 0)
                textViews[i][index].setBackgroundColor(pref.getInt(String.valueOf(i), 0));
        }
    }
}
