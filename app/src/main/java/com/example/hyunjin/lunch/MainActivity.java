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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ProcessTask p;
    BapDownloadTask mProcessTask;
    private android.app.ProgressDialog pd;
    private ImageButton minus_date;
    private ImageButton plus_date;
    private int year;
    private int month;
    private int day;
    private int maxDay;
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

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        Calendar m = Calendar.getInstance();

        maxDay = m.getMaximum(Calendar.DAY_OF_MONTH);

        if (!date_click) {
            year = m.get(Calendar.YEAR);
            month = m.get(Calendar.MONTH);
            day = m.get(Calendar.DATE);
        }

        minus_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar m = Calendar.getInstance();
                plus_date.setVisibility(View.VISIBLE);
                day -= 1;
                if (day <= 0) {
                    day = m.get(Calendar.DATE);
                    minus_date.setVisibility(View.GONE);
                }
                setBtnDate(year, month, day);

            }
        });

        plus_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar m = Calendar.getInstance();
                minus_date.setVisibility(View.VISIBLE);
                day += 1;
                if (day >= maxDay) {
                    plus_date.setVisibility(View.GONE);
                }
                setBtnDate(year, month, day);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarActivity calendarDialog = new CalendarActivity(MainActivity.this);
                calendarDialog.setReturnValue(new ReturnValue() {
                    @Override
                    public void value(int year, int month, int day) {
                        setCalendarDate(year, month, day);
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
            pd.dismiss();

        }
    }

    private void setCalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month - 1;
        this.day = day;

        date_click = true;
        startProcess();
    }

    private void setBtnDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        startProcess();
    }

    private void startProcess() {
        pd = ProgressDialog.show(this, "찾는중!!", "급식이 어딨지...", true, false);

        mProcessTask = new BapDownloadTask(this);
        mProcessTask.execute(year, month, day);

        BapTool.restoreBapDateClass mData = BapTool.restoreBapData(getApplicationContext(), year, month, day);
        ((TextView) findViewById(R.id.main_date_text)).setText(mData.Calender);
        ((TextView) findViewById(R.id.main_meal_text)).setText(mData.Lunch);
        ((TextView) findViewById(R.id.main_etc_text)).setText("칼로리 수치:" + mData.Kcal + "kcal");

        if (mData.Lunch == null || mData.Lunch.equals("")) {
            ((TextView) findViewById(R.id.main_meal_text)).setText("급식이 없습니다.");
            ((TextView) findViewById(R.id.main_etc_text)).setText(" ");
            pd.dismiss();
        }

    }


}
