package com.example.hyunjin.lunch;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.hyunjin.lunch.Meal.BapTool;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Calendar m = Calendar.getInstance();
        BapTool.restoreBapDateClass mData = BapTool.restoreBapData(context, m.get(Calendar.YEAR), m.get(Calendar.MONTH), m.get(Calendar.DATE));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_provider);
        if (mData.Lunch.equals("") || mData.Lunch == null)
            views.setTextViewText(R.id.widget_meal, "급식이 없습니다.");
        views.setTextViewText(R.id.widget_meal, mData.Lunch);
        views.setTextViewText(R.id.widget_date, mData.Calender);

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
}

