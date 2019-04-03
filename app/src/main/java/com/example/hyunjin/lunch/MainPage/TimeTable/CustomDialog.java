package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CustomDialog {
    private ArrayList<String> list;
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void callFunction(final TextView null_txt, final TextView textView, final int n, final int m) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.time_table_custom_dialog);
        dlg.show();

        final AutoCompleteTextView editName = (AutoCompleteTextView) dlg.findViewById(R.id.dialog_edit);
        final Button ok_btn = (Button) dlg.findViewById(R.id.dialog_ok_btn);
        final TextView mainText = (TextView) dlg.findViewById(R.id.dialog_main_text);
        list = new ArrayList<String>();
        editName.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, getStringArrayPref(context, "list_json")));

        switch (m) {
            case 0:
                mainText.setText("월요일   " + String.valueOf(n + 1) + "교시");
                break;
            case 1:
                mainText.setText("화요일   " + String.valueOf(n + 1) + "교시");
                break;
            case 2:
                mainText.setText("수요일   " + String.valueOf(n + 1) + "교시");
                break;
            case 3:
                mainText.setText("목요일   " + String.valueOf(n + 1) + "교시");
                break;
            case 4:
                mainText.setText("금요일   " + String.valueOf(n + 1) + "교시");
                break;
        }

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().length() == 0) {
                    Toast.makeText(context, "과목명을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    switch (m) {
                        case 0:
                            pref = context.getSharedPreferences("mon", MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString(String.valueOf(n), String.valueOf(editName.getText()));
                            editor.commit();
                            break;
                        case 1:
                            pref = context.getSharedPreferences("tue", MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString(String.valueOf(n), String.valueOf(editName.getText()));
                            editor.commit();
                            break;
                        case 2:
                            pref = context.getSharedPreferences("wed", MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString(String.valueOf(n), String.valueOf(editName.getText()));
                            editor.commit();
                            break;
                        case 3:
                            pref = context.getSharedPreferences("thu", MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString(String.valueOf(n), String.valueOf(editName.getText()));
                            editor.commit();
                            break;
                        case 4:
                            pref = context.getSharedPreferences("fri", MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putString(String.valueOf(n), String.valueOf(editName.getText()));
                            editor.commit();
                            break;
                    }

                    pref = context.getSharedPreferences("pref", MODE_PRIVATE);
                    editor = pref.edit();
                    editor.putBoolean("NullText", false);
                    editor.commit();
                    list.add(editName.getText().toString());
                    setStringArrayPref(context, "list_json", list);

                    textView.setText(String.valueOf(editName.getText()));
                    null_txt.setVisibility(View.GONE);
                    dlg.dismiss();
                }
            }
        });
    }

    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    private ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}
