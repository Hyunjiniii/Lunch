package com.example.hyunjin.lunch.MainPage.Setting;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hyunjin.lunch.R;

public class Setting extends Fragment {
    String[] ListMenu = {"문의하기"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_setting, container, false);
        ListView listView = view.findViewById(R.id.setting_listview);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ListMenu);
        listView.setAdapter(adapter);
        return view;
    }
}
