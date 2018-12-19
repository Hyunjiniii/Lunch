package com.example.hyunjin.lunch.MainPage;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.MainActivity;
import com.example.hyunjin.lunch.Meal.BapTool;
import com.example.hyunjin.lunch.Meal.ProcessTask;
import com.example.hyunjin.lunch.R;
import com.ramotion.expandingcollection.ECBackgroundSwitcherView;
import com.ramotion.expandingcollection.ECCardData;
import com.ramotion.expandingcollection.ECPagerView;
import com.ramotion.expandingcollection.ECPagerViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class Lunch extends Fragment {
    private ECPagerView ecPagerView;
    private String[] meal = new String[31];
    BapDownloadTask mProcessTask;
    List<ECCardData> dataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.activity_lunch, container, false);

        // Get pager from layout
        ecPagerView = (ECPagerView) view.findViewById(R.id.ec_pager_element);

        Calendar m = Calendar.getInstance();
        int year = m.get(Calendar.YEAR);
        int month = m.get(Calendar.MONTH);
        int maxDay = m.getMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= maxDay; i++) {
            mProcessTask = new Lunch.BapDownloadTask(getContext());
            mProcessTask.execute(year, month, i);
            BapTool.restoreBapDateClass mData = BapTool.restoreBapData(getContext(), year, month, i);

            if (mData.Lunch == null || mData.Lunch.equals("")) {
                meal[i - 1] = "dd";
            } else {
                meal[i - 1] = mData.Lunch;
            }
        }

        for (int i = 0; i <= maxDay - 1; i++) {
            // Generate example dataset
            dataset = CardDataImpl.generateExampleData(meal[i], i);
        }


        // Implement pager adapter and attach it to pager view
        ECPagerViewAdapter ecPagerViewAdapter = new ECPagerViewAdapter(getContext(), dataset) {
            @Override
            public void instantiateCard(LayoutInflater inflaterService, ViewGroup head, final ListView list, ECCardData data) {
                // Data object for current card
                CardDataImpl cardData = (CardDataImpl) data;

                // Set adapter and items to current card content list
                final List<String> listItems = cardData.getListItems();
                final CardListItemAdapter listItemAdapter = new CardListItemAdapter(getContext(), listItems);
                list.setAdapter(listItemAdapter);
                // Also some visual tuning can be done here
                list.setBackgroundColor(Color.WHITE);

                // Here we can create elements for head view or inflate layout from xml using inflater service
                TextView cardTitle = new TextView(getContext());
                cardTitle.setText(cardData.getCardTitle());
                cardTitle.setTextSize(COMPLEX_UNIT_DIP, 20);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                head.addView(cardTitle, layoutParams);

                // Card toggling by click on head element
//                head.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(final View v) {
//                        ecPagerView.toggle();
//                    }
//                });
            }
        };
        ecPagerView.setPagerViewAdapter(ecPagerViewAdapter);

        // Add background switcher to pager view
//        ecPagerView.setBackgroundSwitcherView((ECBackgroundSwitcherView) view.findViewById(R.id.ec_bg_switcher_element));

        // Directly modifying dataset
        ecPagerViewAdapter.notifyDataSetChanged();

        return view;

    }

    public class BapDownloadTask extends ProcessTask {
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
