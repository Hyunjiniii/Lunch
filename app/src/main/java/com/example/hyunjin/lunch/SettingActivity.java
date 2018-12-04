package com.example.hyunjin.lunch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listView = (ListView) findViewById(R.id.setting_listview);

        item = new String[2];
        item[0] = "알림 시간 설정";
        item[1] = "피드";

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);

        listView.setAdapter(adapter);
    }
}
