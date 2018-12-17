package com.example.hyunjin.lunch.MainPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hyunjin.lunch.R;

import java.util.List;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.ViewHolder> {
    private Context context;
    private List<LunchItem> items;

    public LunchAdapter(List<LunchItem> items, Context context) {
        this.items = items;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.lunch_center_item, viewGroup, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        final LunchItem contentItem = items.get(i);

        holder.day_text.setText(contentItem.getDay());
        holder.day_of_week_text.setText(contentItem.getDay_of_week());
        holder.meal_text.setText(contentItem.getMeal());

        if (contentItem.getMeal() == null || contentItem.getMeal().equals(""))
            holder.meal_text.setText("밥 없다");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView day_text;
        TextView day_of_week_text;
        TextView meal_text;

        public ViewHolder(View item) {
            super(item);

            day_text = item.findViewById(R.id.lunch_center_item_day);
            day_of_week_text = item.findViewById(R.id.lunch_center_item_day_of_week);
            meal_text = item.findViewById(R.id.lunch_center_item_meal);
        }
    }
}
