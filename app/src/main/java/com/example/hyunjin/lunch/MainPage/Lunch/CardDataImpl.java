package com.example.hyunjin.lunch.MainPage.Lunch;

import android.content.Context;
import android.util.Log;

import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.example.hyunjin.lunch.R;
import com.ramotion.expandingcollection.ECCardData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardDataImpl implements ECCardData<String> {
    private String cardTitle;
    private Integer mainBackgroundResource;
    private Integer headBackgroundResource;
    private List<String> listItems;

    public CardDataImpl(String cardTitle, Integer mainBackgroundResource, Integer headBackgroundResource, List<String> listItems) {
        this.mainBackgroundResource = mainBackgroundResource;
        this.headBackgroundResource = headBackgroundResource;
        this.listItems = listItems;
        this.cardTitle = cardTitle;
    }

    @Override
    public Integer getMainBackgroundResource() {
        return mainBackgroundResource;
    }

    @Override
    public Integer getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    @Override
    public List<String> getListItems() {
        return listItems;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public static List<ECCardData> generateExampleData(Context context) {
        List<ECCardData> list = new ArrayList<>();
        Calendar m = Calendar.getInstance();
        int month = m.get(Calendar.MONTH) + 1;

        for (int i = 1; i <= month; i++) {
            list.add(new CardDataImpl(i + "월", R.drawable.card_design, R.drawable.card_design, createItemsList(context, i)));
        }

        return list;
    }

    private static List<String> createItemsList(Context context, int mmm) {
        ArrayList<String> list = new ArrayList<>();
        BapDownloadTask mProcessTask;
        Calendar m = Calendar.getInstance();
        int year = m.get(Calendar.YEAR);
        int maxDay = m.getMaximum(Calendar.DAY_OF_MONTH);
        int null_day = 0;

        for (int j = 1; j <= maxDay; j++) {
            mProcessTask = new BapDownloadTask(context);
            mProcessTask.execute(year, mmm - 1, j);
            BapTool.restoreBapDateClass mData = BapTool.restoreBapData(context, year, mmm - 1, j);
            if (mData.Lunch == null || mData.Lunch.equals("")) {
                null_day += 1;
                list.add(year + "년 " + mmm + "월 " + j + "일\n\n" + "밥 없다");
                if (null_day >= 28){
                    list.clear();
                    list.add("급식 정보가 없습니다.");
                    break;
                }
            }
            else
                list.add(year + "년 " + mmm + "월 " + j + "일\n\n" + mData.Lunch);



        }

        return list;
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
