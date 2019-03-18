package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hyunjin.lunch.Meal.TableItems;
import com.example.hyunjin.lunch.R;

import java.util.ArrayList;

public class TimeTableAdd extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<TableItems> items = new ArrayList<>();
    private TableListAdapter adapter;
    private boolean mon_cnt = true, tue_cnt = true, wed_cnt = true, thu_cnt = true, fri_cnt = true;
    private EditText editText;
    private Button mon_btn, tue_btn, wed_btn, thu_btn, fri_btn, ok_btn;
    private int date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_add);

        mon_btn = (Button) findViewById(R.id.time_add_mon_btn);
        tue_btn = (Button) findViewById(R.id.time_add_tue_btn);
        wed_btn = (Button) findViewById(R.id.time_add_wed_btn);
        thu_btn = (Button) findViewById(R.id.time_add_thu_btn);
        fri_btn = (Button) findViewById(R.id.time_add_fri_btn);
        ok_btn = (Button) findViewById(R.id.time_add_ok_btn);
        editText = (EditText) findViewById(R.id.time_table_name);
        adapter = new TableListAdapter(items, TimeTableAdd.this);
        recyclerView = (RecyclerView) findViewById(R.id.time_table_add_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("월요일");
                if (mon_cnt) {
                    mon_cnt = false;
                    items.add(0, item);
                    adapter.notifyDataSetChanged();
                } else {
                    mon_cnt = true;
                    items.remove(0);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        tue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("화요일");
                if (tue_cnt) {
                    tue_cnt = false;
                    items.add(1, item);
                    adapter.notifyDataSetChanged();
                } else {
                    tue_cnt = true;
                    items.remove(1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        wed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("수요일");
                if (wed_cnt) {
                    wed_cnt = false;
                    items.add(2, item);
                    adapter.notifyDataSetChanged();
                } else {
                    wed_cnt = true;
                    items.remove(2);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        thu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("목요일");
                if (thu_cnt) {
                    thu_cnt = false;
                    items.add(3, item);
                    adapter.notifyDataSetChanged();
                } else {
                    thu_cnt = true;
                    items.remove(3);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        fri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("금요일");
                if (fri_cnt) {
                    fri_cnt = false;
                    items.add(4, item);
                    adapter.notifyDataSetChanged();
                } else {
                    fri_cnt = true;
                    items.remove(4);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeTable table = new TimeTable();
                String name = String.valueOf(editText.getText());
                Toast.makeText(TimeTableAdd.this, String.valueOf(date) + String.valueOf(time), Toast.LENGTH_SHORT).show();
                if (date != -1 && name != null) {
                    table.setData(date, time, name);
                    finish();
                } else {
                    Toast.makeText(TimeTableAdd.this, "과목명과 시간을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setId(int date, int time) {
        this.date = date;
        this.time = time;
    }
}
