package com.example.hyunjin.lunch.TimeTablePage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hyunjin.lunch.R;

import java.util.List;

public class Friday extends Fragment {

    public static Friday newInstance() {
        Friday friday = new Friday();
        return friday;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.time_table_friday, container, false);


        return view;
    }
}
