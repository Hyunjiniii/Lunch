package com.example.hyunjin.lunch.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hyunjin.lunch.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView CalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        CalendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SUNDAY))
                .setMinimumDate(CalendarDay.from(2018, 1, 1))
                .setMaximumDate(CalendarDay.from(2018, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

    }
}
