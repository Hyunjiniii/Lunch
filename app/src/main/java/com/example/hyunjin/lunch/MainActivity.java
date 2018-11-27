package com.example.hyunjin.lunch;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.Calendar.CalendarActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Runnable {
    ProcessTask p;
    long backPressedTime;
    private android.app.ProgressDialog pd;
    private int num;
    private ImageButton minus_date;
    private ImageButton plus_date;
    private int year;
    private int month;
    private int day;
    private String nowDate;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.main_actionbar);
        setContentView(R.layout.activity_main);

        minus_date = (ImageButton) findViewById(R.id.main_minus_date_btn);
        plus_date = (ImageButton) findViewById(R.id.main_plus_date_btn);
        date = (TextView) findViewById(R.id.main_date_text);

        minus_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num -= 1;
                setData(num);
                if (num == 0)
                    minus_date.setVisibility(View.GONE);
                else {
                    minus_date.setVisibility(View.VISIBLE);
                    plus_date.setVisibility(View.VISIBLE);
                }
            }
        });

        plus_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num += 1;
                setData(num);
                if (num == 6)
                    plus_date.setVisibility(View.GONE);
                else {
                    plus_date.setVisibility(View.VISIBLE);
                    minus_date.setVisibility(View.VISIBLE);
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarActivity calendarDialog = new CalendarActivity(MainActivity.this);
                calendarDialog.show();

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                Window window = calendarDialog.getWindow();

                int x = (int)(size.x * 1.0f);
                int y = (int)(size.y * 0.7f);

                window.setLayout(x, y);
            }
        });

        p = new ProcessTask();
        p.execute();
        processParsing();
    }

    public void processParsing() {
//         메인 뷰에 ProgressDialog 표시 시작
        pd = ProgressDialog.show(this, "찾는중!!", "급식이 어딨지...", true, false);

        Thread thread = new Thread(this);
        thread.start(); // run() 실행
    }

    public void run() {
    }

//    public void onBtnsClick(View target) {
//        if (target.equals("-")) {
//            setData(num - 1);
//        } else {
//            setData(num + 1);
//        }
//    }

    public void setData(int index) {
        if (p.meal[index].equals(" ")) {
            ((TextView) findViewById(R.id.main_date_text)).setText(p.date[index]);
            ((TextView) findViewById(R.id.main_meal_text)).setText("급식이 없습니다.");
            ((TextView) findViewById(R.id.main_etc_text)).setText("(휴일,재량휴업일, 등등)");
            return;
        }
        ((TextView) findViewById(R.id.main_date_text)).setText(p.date[index]);
        ((TextView) findViewById(R.id.main_meal_text)).setText(p.meal[index]);
        ((TextView) findViewById(R.id.main_etc_text)).setText("칼로리 수치:" + p.kcal[index] + "kcal");
    }

    public class ProcessTask extends AsyncTask<String, Integer, Long> {

        public String[] meal = new String[7];
        public String[] date = new String[7];
        public String[] kcal = new String[7];

        @Override
        protected Long doInBackground(String... params) {
            String CountryCode = "sen.go.kr";//접속 할 교육청 도메인(ex.강원도 교육청=kwe.go.kr | 경기도 교육청=goe.go.kr...)
            String schulCode = "B100000662";//학교 고유 코드
            String schulCrseScCode = "4";//일반적으로 4. 파싱이상현상생길 시 3
            String schulKndScCode = "04";//중학교는 03,고등학교는04
            String schMmealScCode = "2";//중식 식사코드
            try {
                meal = MealLibrary.getMealNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode);
                date = MealLibrary.getDateNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode);
                kcal = MealLibrary.getKcalNew(CountryCode, schulCode, schulCrseScCode, schulKndScCode, schMmealScCode);
            } catch (Exception e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "오류",
                                Toast.LENGTH_LONG).show();
                    }
                });
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsStrting = sw.toString();

                Log.e("ll", exceptionAsStrting);
                pd.dismiss();
                finish();
                return -1l;
            }

            for (int i = 1; i <= 5; i++) {
                if (meal[i].equals(" ")) continue;
                StringBuffer sb = new StringBuffer();
                int len = meal[i].length();
                int index = 0;
                boolean flag = false;
                while (index < len) {
                    char c = meal[i].charAt(index);
                    if (c == '(') flag = true;
                    if (flag == false) sb.append(c);
                    if (c == ')') flag = false;

                    index++;
                }

                meal[i] = sb.toString();
            }
            pd.dismiss();
            return 0l;
        }

        protected void onPostExecute(Long result) {
            if (result == -1l) {
                pd.dismiss();
                return;
            }
            Calendar m = Calendar.getInstance();
            year = m.get(Calendar.YEAR);
            month = m.get(Calendar.MONTH) + 1;
            day = m.get(Calendar.DATE);
            nowDate = String.format("%04d", year) + "." + String.format("%02d", month) + "." + String.format("%02d", day);

            for (int i = 0; i <= 6; i++) {
                if (date[i].indexOf(nowDate) != -1) {
                    Log.d("date_", String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
                    num = date[i].indexOf(nowDate);
                    if (i == 0 || i == 6 || meal[i].equals(" ")) {
                        ((TextView) findViewById(R.id.main_date_text)).setText(date[i]);
                        ((TextView) findViewById(R.id.main_meal_text)).setText("급식이 없습니다.\n");
                        ((TextView) findViewById(R.id.main_etc_text)).setText("(휴일, 재량휴업일, 등등)");
                        break;
                    }
                    ((TextView) findViewById(R.id.main_date_text)).setText(date[i]);
                    ((TextView) findViewById(R.id.main_meal_text)).setText(meal[i]);
                    ((TextView) findViewById(R.id.main_etc_text)).setText("칼로리 수치:" + kcal[i] + " kcal");
                    break;
                }
            }
            super.onPostExecute(result);
            handler.sendEmptyMessage(0);
        }

        private Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                pd.dismiss();
            }
        };

        private void parseOpenAPI() {
        }
    }
}
