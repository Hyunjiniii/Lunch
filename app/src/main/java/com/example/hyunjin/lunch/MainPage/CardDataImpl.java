package com.example.hyunjin.lunch.MainPage;

import android.content.Context;
import android.util.Log;

import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.example.hyunjin.lunch.R;
import com.ramotion.expandingcollection.ECCardData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static java.security.AccessController.getContext;

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

    public static List<ECCardData> generateExampleData(String meal, int i) {
        List<ECCardData> list = new ArrayList<>();
        String[] cardMeal = new String[31];
        cardMeal[i] = meal;

        for (int j = 0; j <= i; j++) {
            if (cardMeal[j] != null) {
                Log.d("Card", cardMeal[j] + "  " + String.valueOf(j));
            }
            list.add(new CardDataImpl(cardMeal[0], R.drawable.background, R.drawable.background, createItemsList(cardMeal[0])));
        }
        return list;
    }

    private static List<String> createItemsList(String cardName) {
        ArrayList<String> list = new ArrayList<>();
//        list.addAll(
//                Arrays.asList(
//                cardName + " - Item 1",
//                cardName + " - Item 2",
//                cardName + " - Item 3",
//                cardName + " - Item 4",
//                cardName + " - Item 5",
//                cardName + " - Item 6",
//                cardName + " - Item 7"
//        ));

        return list;
    }


}
