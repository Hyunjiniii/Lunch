package com.example.hyunjin.lunch;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hyunjin.lunch.Calendar.CalendarActivity;
import com.example.hyunjin.lunch.Calendar.ReturnValue;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ProcessTask p;
    BapDownloadTask mProcessTask;
    private android.app.ProgressDialog pd;
    private int num;
    private ImageButton minus_date;
    private ImageButton plus_date;
    private int year;
    private int month;
    private int day;
    private String nowDate;
    private TextView date;
    private boolean date_click = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.main_actionbar);
        setContentView(R.layout.activity_main);

        minus_date = (ImageButton) findViewById(R.id.main_minus_date_btn);
        plus_date = (ImageButton) findViewById(R.id.main_plus_date_btn);
        date = (TextView) findViewById(R.id.main_date_text);

        Calendar m = Calendar.getInstance();

        if (!date_click) {
            year = m.get(Calendar.YEAR);
            month = m.get(Calendar.MONTH);
            day = m.get(Calendar.DATE);
        }

//        minus_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                num -= 1;
//                setData(num);
//                if (num == 0)
//                    minus_date.setVisibility(View.GONE);
//                else {
//                    minus_date.setVisibility(View.VISIBLE);
//                    plus_date.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        plus_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                num += 1;
//                setData(num);
//                if (num == 6)
//                    plus_date.setVisibility(View.GONE);
//                else {
//                    plus_date.setVisibility(View.VISIBLE);
//                    minus_date.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarActivity calendarDialog = new CalendarActivity(MainActivity.this);
                calendarDialog.setReturnValue(new ReturnValue() {
                    @Override
                    public void value(int year, int month, int day) {
                        setResult(year, month, day);
                    }
                });
                calendarDialog.show();

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                Window window = calendarDialog.getWindow();

                int x = (int) (size.x * 0.9f);
                int y = (int) (size.y * 0.6f);

                window.setLayout(x, y);
            }
        });

        startProcess();
    }

    public class BapDownloadTask extends com.example.hyunjin.lunch.ProcessTask {
        public BapDownloadTask(Context mContext) {
            super(mContext);
        }

        @Override
        public void onPreDownload() {
            // 다운로드 전에 해야하는 코드를 여기에 작성하세요
        }

        @Override
        public void onUpdate(int progress) {
            // 진행상황을 표시할수 있는 코드를 여기에 작성하세요
            pd.setProgress(progress);
        }

        @Override
        public void onFinish(long result) {
            // 급식을 가져오는 코드를 여기에 작성하세요
//            if (result  == -1) {
                pd.dismiss();
//            }


        }
    }

    private void setResult(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        date_click = true;
        startProcess();
    }

    private void startProcess() {
        pd = ProgressDialog.show(this, "찾는중!!", "급식이 어딨지...", true, false);

        mProcessTask = new BapDownloadTask(this);
        mProcessTask.execute(year, month, day);

        BapTool.restoreBapDateClass mData = BapTool.restoreBapData(getApplicationContext(), year, month, day);
        ((TextView) findViewById(R.id.main_date_text)).setText(mData.Calender);
        ((TextView) findViewById(R.id.main_meal_text)).setText(mData.Lunch);
        ((TextView) findViewById(R.id.main_etc_text)).setText("칼로리 수치:"+ mData.Kcal + "kcal");
    }


}
