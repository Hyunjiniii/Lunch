package com.example.hyunjin.lunch_center_item;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.hyunjin.lunch.MainPage.Lunch.CardDataImpl;
import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.example.hyunjin.lunch.R;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        BapDownloadTask mProcessTask;
        Calendar m = Calendar.getInstance();
        int month = m.get(Calendar.MONTH) + 1;
        int mmm = month - 1;
        int year = m.get(Calendar.YEAR);
        int date = m.get(Calendar.DATE);
        mProcessTask = new BapDownloadTask(context);
        mProcessTask.execute(year, mmm, date);
        BapTool.restoreBapDateClass mData = BapTool.restoreBapData(context, year, mmm, date);


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, mData.Lunch);
        views.setTextViewText(R.id.appwidget_date, mData.Calender + " " + mData.DayOfTheWeek);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static class BapDownloadTask extends ProcessTask {
        public BapDownloadTask(Context mContext) {
            super(mContext);
        }

        @Override
        public void onPreDownload() {
        }

        @Override
        public void onUpdate(int progress) {
        }

        @Override
        public void onFinish(long result) {

        }
    }

}

