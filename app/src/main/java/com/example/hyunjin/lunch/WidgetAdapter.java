package com.example.hyunjin.lunch;

public class WidgetAdapter {
    public CharSequence mealText;
    public CharSequence dateText;

    public void setDateText(CharSequence dateText) {
        this.dateText = dateText;
    }

    public void setMealText(CharSequence mealText) {
        this.mealText = mealText;
    }

    public CharSequence getDateText() {
        return dateText;
    }

    public CharSequence getMealText() {
        return mealText;
    }
}
