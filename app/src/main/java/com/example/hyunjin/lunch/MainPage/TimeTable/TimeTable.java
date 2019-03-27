package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.content.Context;
import android.content.SharedPreferences;
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
        final TextView null_txt = (TextView) view.findViewById(R.id.time_table_null_txt);
        SharedPreferences pref = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if (pref.getBoolean("NullText", true))
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
                        CustomDialog customDialog = new CustomDialog(getContext());
                        customDialog.callFunction(null_txt, textViews[finalI][finalJ], finalI, finalJ);
                    }
                });
            }
        }

        getPreferences("mon");
        getPreferences("tue");
        getPreferences("wed");
        getPreferences("thu");
        getPreferences("fri");

        return view;
    }

    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

    private void getPreferences(String date) {
        switch (date) {
            case "mon":
                for (int i = 0; i <= 6; i++) {
                    SharedPreferences pref = getContext().getSharedPreferences("mon", Context.MODE_PRIVATE);
                    if (pref.getString(String.valueOf(i), null) != null)
                        textViews[i][0].setText(pref.getString(String.valueOf(i), null));
                }
                break;
            case "tue":
                for (int i = 0; i <= 6; i++) {
                    SharedPreferences pref = getContext().getSharedPreferences("tue", Context.MODE_PRIVATE);
                    if (pref.getString(String.valueOf(i), null) != null)
                        textViews[i][1].setText(pref.getString(String.valueOf(i), null));
                }
                break;
            case "wed":
                for (int i = 0; i <= 6; i++) {
                    SharedPreferences pref = getContext().getSharedPreferences("wed", Context.MODE_PRIVATE);
                    if (pref.getString(String.valueOf(i), null) != null)
                        textViews[i][2].setText(pref.getString(String.valueOf(i), null));
                }
                break;
            case "thu":
                for (int i = 0; i <= 6; i++) {
                    SharedPreferences pref = getContext().getSharedPreferences("thu", Context.MODE_PRIVATE);
                    if (pref.getString(String.valueOf(i), null) != null)
                        textViews[i][3].setText(pref.getString(String.valueOf(i), null));
                }
                break;
            case "fri":
                for (int i = 0; i <= 6; i++) {
                    SharedPreferences pref = getContext().getSharedPreferences("fri", Context.MODE_PRIVATE);
                    if (pref.getString(String.valueOf(i), null) != null)
                        textViews[i][4].setText(pref.getString(String.valueOf(i), null));
                }
                break;

        }


    }
}
