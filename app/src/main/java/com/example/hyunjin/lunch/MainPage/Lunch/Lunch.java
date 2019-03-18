package com.example.hyunjin.lunch.MainPage.Lunch;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.R;
import com.ramotion.expandingcollection.ECCardData;
import com.ramotion.expandingcollection.ECPagerView;
import com.ramotion.expandingcollection.ECPagerViewAdapter;

import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

public class Lunch extends Fragment {
    private ECPagerView ecPagerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.activity_lunch, container, false);

        // Get pager from layout
        ecPagerView = (ECPagerView) view.findViewById(R.id.ec_pager_element);

        List<ECCardData> dataset = CardDataImpl.generateExampleData(getContext());

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
                final TextView cardTitle = new TextView(getContext());
                cardTitle.setText(cardData.getCardTitle());
                cardTitle.setTextColor(Color.BLACK);
                cardTitle.setTextSize(COMPLEX_UNIT_DIP, 16);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.gravity = Gravity.CENTER;
                head.addView(cardTitle, layoutParams);

                // Card toggling by click on head element
                head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        Toast.makeText(getContext(), String.valueOf(cardTitle.getText()), Toast.LENGTH_SHORT).show();
                        ecPagerView.toggle();
                    }
                });
            }
        };
        ecPagerView.setPagerViewAdapter(ecPagerViewAdapter);

        // Add background switcher to pager view
//        ecPagerView.setBackgroundSwitcherView((ECBackgroundSwitcherView) view.findViewById(R.id.ec_bg_switcher_element));

        // Directly modifying dataset
        ecPagerViewAdapter.notifyDataSetChanged();

        final ItemsCountView itemsCountView = (ItemsCountView) view.findViewById(R.id.items_count_view);
        ecPagerView.setOnCardSelectedListener(new ECPagerView.OnCardSelectedListener() {
            @Override
            public void cardSelected(int newPosition, int oldPosition, int totalElements) {
                itemsCountView.update(newPosition, oldPosition, totalElements);
            }
        });

        return view;

    }
}
