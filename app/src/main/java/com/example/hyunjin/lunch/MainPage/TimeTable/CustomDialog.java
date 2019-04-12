package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
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
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button picker_btn;
    private int select;
    private String text = null;
    private Drawable background = null;
    private AutoCompleteTextView editName;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public CustomDialog(Context context, String text, Drawable background) {
        this.context = context;
        this.text = text;
        this.background = background;
    }

    public void callFunction(final TextView null_txt, final TextView textView, final int n, final int m) {
        final Dialog dlg = new Dialog(context);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.time_table_custom_dialog);
        dlg.show();

        final Button ok_btn = (Button) dlg.findViewById(R.id.dialog_ok_btn);
        final TextView mainText = (TextView) dlg.findViewById(R.id.dialog_main_text);
        editName = (AutoCompleteTextView) dlg.findViewById(R.id.dialog_edit);
        picker_btn = (Button) dlg.findViewById(R.id.dialog_color_picker_btn);

        list = new ArrayList<String>();
        editName.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, getStringArrayPref(context, "list_json")));

        if (text != null)
            editName.setText(text);
        if (background != null) {
            int color = Color.TRANSPARENT;
            if (background instanceof ColorDrawable)
                color = ((ColorDrawable) background).getColor();
            picker_btn.setBackgroundColor(color);
        }

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

        picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().length() == 0) {
                    Toast.makeText(context, "과목명을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    switch (m) {
                        case 0:
                            set("mon", n);
                            break;
                        case 1:
                            set("tue", n);
                            break;
                        case 2:
                            set("wed", n);
                            break;
                        case 3:
                            set("thu", n);
                            break;
                        case 4:
                            set("fri", n);
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

    private void openColorPicker() {
        final ColorPicker colorPicker = new ColorPicker((Activity) context);
        ArrayList<String> colors = new ArrayList<>();

        colors.add("#ffab91");
        colors.add("#F48FB1");
        colors.add("#ce93d8");
        colors.add("#b39ddb");
        colors.add("#9fa8da");
        colors.add("#90caf9");
        colors.add("#81d4fa");
        colors.add("#80deea");
        colors.add("#80cbc4");
        colors.add("#c5e1a5");
        colors.add("#e6ee9c");
        colors.add("#fff59d");
        colors.add("#ffe082");
        colors.add("#ffcc80");
        colors.add("#bcaaa4");

        colorPicker.setColors(colors)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        Toast.makeText(context, String.valueOf(color), Toast.LENGTH_SHORT).show();
                        picker_btn.setBackgroundColor(color);
                        select = color;
                    }

                    @Override
                    public void onCancel() {
                        colorPicker.dismissDialog();
                    }
                }).show();
    }

    private void set(String date, int index) {
        pref = context.getSharedPreferences(date, MODE_PRIVATE);
        editor = pref.edit();
        editor.putString(String.valueOf(index), String.valueOf(editName.getText()));
        editor.putInt(String.valueOf(index) + 1, select);
        editor.commit();
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
