package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.R;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog {
    private Context context;
    private String s = null;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void callFunction(final TextView textView, int n, int m) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.time_table_custom_dialog);
        dlg.show();

        final EditText editName = (EditText) dlg.findViewById(R.id.dialog_edit);
        final Button ok_btn = (Button) dlg.findViewById(R.id.dialog_ok_btn);
        final TextView mainText = (TextView) dlg.findViewById(R.id.dialog_main_text);

        switch (m) {
            case 0:
                mainText.setText("월요일 " + String.valueOf(n + 1) + "교시");
                break;
            case 1:
                mainText.setText("화요일 " + String.valueOf(n + 1) + "교시");
                break;
            case 2:
                mainText.setText("수요일 " + String.valueOf(n + 1) + "교시");
                break;
            case 3:
                mainText.setText("목요일 " + String.valueOf(n + 1) + "교시");
                break;
            case 4:
                mainText.setText("금요일 " + String.valueOf(n + 1) + "교시");
                break;
        }

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().length() == 0) {
                    Toast.makeText(context, "과목명을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText(String.valueOf(editName.getText()));
                    Log.d("EditName", String.valueOf(editName.getText()));
                    dlg.dismiss();
                }
            }
        });

    }
}
