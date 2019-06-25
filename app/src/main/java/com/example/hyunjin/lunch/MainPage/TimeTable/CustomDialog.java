package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hyunjin.lunch.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

import static android.content.Context.MODE_PRIVATE;

public class CustomDialog {
    private ArrayList<String> list;
    private Context context;
    private Button picker_btn;
    private int select = 0;
    private String text = null;
    private Drawable background = null;
    private AutoCompleteTextView textInput;
    private TextView mainText;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public CustomDialog(Context context, String text, Drawable background) {
        this.context = context;
        this.text = text;
        this.background = background;
    }

    public void callFunction(final TextView textView, final int row, final int column) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.time_table_custom_dialog);
        dlg.show();

        mainText = (TextView) dlg.findViewById(R.id.dialog_main_text);
        textInput = (AutoCompleteTextView) dlg.findViewById(R.id.dialog_edit);
        picker_btn = (Button) dlg.findViewById(R.id.dialog_color_picker_btn);

        list = new ArrayList<String>();
        textInput.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, getStringArrayPref(context, "list_json")));

        // 설정해둔 시간표 수정 시 EditText와 배경색상 선택버튼 데이터 설정
        isData();

        // 다이얼로그 타이틀 설정
        setMainText(row, column);

        // 배경색 설정 창 open
        picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        Button ok_btn = (Button) dlg.findViewById(R.id.dialog_ok_btn);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInput.getText().toString().length() != 0) {
                    setPrefData(row, column);

                    list.add(textInput.getText().toString());
                    setStringArrayPref(context, "list_json", list);

                    textView.setText(String.valueOf(textInput.getText()));
                    if (select != 0)
                        textView.setBackgroundColor(select);
                    dlg.dismiss();

                } else
                    Toast.makeText(context, "과목명을 입력해주세요", Toast.LENGTH_SHORT).show();

            }
        });

        textInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String input_text = ((TextView)view).getText().toString();
                Log.d("Clicked_Item : ", input_text);

                for (int column = 0; column <= 4; column++) {
                    SharedPreferences inner_pref = context.getSharedPreferences(column + "요일", MODE_PRIVATE);
                    for (int row = 0; row <= 6; row++) {
                        String prefString = inner_pref.getString(row + "교시_text", null);

                        if (prefString != null && prefString.equals(input_text)) {
                            text = inner_pref.getString(row + "교시_text", null);
                            select = inner_pref.getInt(row + "교시_color", 0);
                            picker_btn.setBackgroundColor(select);
                            setPrefData(row, column);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void openColorPicker() {
        final ColorPicker colorPicker = new ColorPicker((Activity) context);
        ArrayList<String> colors = new ArrayList<>();

        colors.add("#FFCCBC");
        colors.add("#F8BBD0");
        colors.add("#E1BEE7");
        colors.add("#D1C4E9");
        colors.add("#BBDEFB");
        colors.add("#B2EBF2");
        colors.add("#B2DFDB");
        colors.add("#C8E6C9");
        colors.add("#F0F4C3");
        colors.add("#fff9c4");
        colors.add("#ffecb3");
        colors.add("#ffe0b2");
        colors.add("#d7ccc8");
        colors.add("#cfd8dc");
        colors.add("#ffffff");

        colorPicker.setColors(colors)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        picker_btn.setBackgroundColor(color);
                        select = color;
                    }

                    @Override
                    public void onCancel() {
                        colorPicker.dismissDialog();
                    }
                }).show();
    }

    private void setPrefData(int row, int column) {
        SharedPreferences pref = context.getSharedPreferences(column + "요일", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(row + "교시_text", textInput.getText().toString());
        editor.putInt(row + "교시_color", select);
        editor.commit();
    }

    // 과목명 자동완성을 위해 list를 preferences에 저장
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

    private void isData() {
        // 이미 과목명이 있는 경우 EditText에 넣어줌
        if (text != null)
            textInput.setText(text);

        // 이미 배경색이 있는 경우 배경색 선택 버튼에 색상 설정
        if (background != null) {
            int color = Color.TRANSPARENT;
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();
            picker_btn.setBackgroundColor(color);
        }
    }

    private void setMainText(int row, int column) {
        switch (column) {
            case 0:
                mainText.setText("월요일   " + (row + 1) + "교시");
                break;
            case 1:
                mainText.setText("화요일   " + (row + 1) + "교시");
                break;
            case 2:
                mainText.setText("수요일   " + (row + 1) + "교시");
                break;
            case 3:
                mainText.setText("목요일   " + (row + 1) + "교시");
                break;
            case 4:
                mainText.setText("금요일   " + (row + 1) + "교시");
                break;
        }
    }

}
