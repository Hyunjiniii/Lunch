package com.example.hyunjin.lunch.MainPage;

public class LunchItem {
    private String day;
    private String day_of_week;
    private String meal;

    public LunchItem() {

    }

    public LunchItem(String day, String day_of_week, String meal) {
        this.day = day;
        this.day_of_week = day_of_week;
        this.meal = meal;
    }

    public String getDay() {
        return day;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public String getMeal() {
        return meal;
    }
}
