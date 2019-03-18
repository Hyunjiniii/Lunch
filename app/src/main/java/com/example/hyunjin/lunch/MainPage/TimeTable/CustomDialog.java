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
import android.widget.ListView;

import com.example.hyunjin.lunch.R;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog {
    private Context context;
    private String s;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void callFunction(final int date, final Button button) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.time_table_custom_dialog);
        dlg.show();

        final ListView listView = (ListView) dlg.findViewById(R.id.time_table_custom_dialog_listview);
        final List<String> list = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
        final TimeTableAdd timeTableAdd = new TimeTableAdd();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                s = (String) adapterView.getItemAtPosition(i);
                button.setText(s);
                timeTableAdd.setId(date, Integer.parseInt(String.valueOf(button.getText()).substring(0, 1)));
                Log.d("date", String.valueOf(date));
                dlg.dismiss();
            }
        });

        list.add("1교시");
        list.add("2교시");
        list.add("3교시");
        list.add("4교시");
        list.add("5교시");
        list.add("6교시");
        list.add("7교시");
    }
}
