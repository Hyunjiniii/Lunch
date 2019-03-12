package com.example.hyunjin.lunch.MainPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.hyunjin.lunch.Meal.TableItems;
import com.example.hyunjin.lunch.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeTableAdd extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<TableItems> items = new ArrayList<>();
    private TableListAdapter adapter;
    private int mon_cnt = 0, tue_cnt = 0, wed_cnt = 0, thu_cnt = 0, fri_cnt = 0;
    private Button mon_btn, tue_btn, wed_btn, thu_btn, fri_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_add);

        mon_btn = (Button) findViewById(R.id.time_add_mon_btn);
        tue_btn = (Button) findViewById(R.id.time_add_tue_btn);
        wed_btn = (Button) findViewById(R.id.time_add_wed_btn);
        thu_btn = (Button) findViewById(R.id.time_add_thu_btn);
        fri_btn = (Button) findViewById(R.id.time_add_fri_btn);
        adapter = new TableListAdapter(items, TimeTableAdd.this);
        recyclerView = (RecyclerView) findViewById(R.id.time_table_add_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        mon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("월요일");
                mon_cnt += 1;
                if (mon_cnt % 2 == 1) {
                    items.add(0, item);
                    adapter.notifyDataSetChanged();
                } else {
                    items.remove(0);
                    adapter.notifyDataSetChanged();
                }

            }
        });
        tue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("화요일");
                tue_cnt += 1;
                if (tue_cnt % 2 == 1) {
                    items.add(1, item);
                    adapter.notifyDataSetChanged();
                } else {
                    items.remove(1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        wed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("수요일");
                wed_cnt += 1;
                if (wed_cnt % 2 == 1) {
                    items.add(2, item);
                    adapter.notifyDataSetChanged();
                } else {
                    items.remove(2);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        thu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("목요일");
                thu_cnt += 1;
                if (thu_cnt % 2 == 1) {
                    items.add(3, item);
                    adapter.notifyDataSetChanged();
                } else {
                    items.remove(3);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        fri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableItems item = new TableItems("금요일");
                fri_cnt += 1;
                if (fri_cnt % 2 == 1) {
                    items.add(4, item);
                    adapter.notifyDataSetChanged();
                } else {
                    items.remove(4);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
