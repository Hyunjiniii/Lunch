package com.example.hyunjin.lunch.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.hyunjin.lunch.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;

public class CalendarActivity extends Dialog {
    private MaterialCalendarView CalendarView;
    private ReturnValue ReturnValue;
    private Context context;
    private int year;
    private int month;
    private int day;

    public CalendarActivity(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setReturnValue(ReturnValue ReturnValue) {
        this.ReturnValue = ReturnValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        CalendarView.state().edit()
                .setFirstDayOfWeek(DayOfWeek.of(Calendar.SATURDAY))
                .setMinimumDate(CalendarDay.from(2018, 1, 1))
                .setMaximumDate(CalendarDay.from(2018, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        CalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {
                year = calendarDay.getYear();
                month = calendarDay.getMonth();
                day = calendarDay.getDay();

                ReturnValue.value(year, month, day);

                dismiss();
            }
        });
    }
}
