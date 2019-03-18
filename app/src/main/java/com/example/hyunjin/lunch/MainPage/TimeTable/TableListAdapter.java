package com.example.hyunjin.lunch.MainPage.TimeTable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hyunjin.lunch.Meal.TableItems;
import com.example.hyunjin.lunch.R;

import java.util.List;

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.ViewHolder> {
    private List<TableItems> items;
    private Context context;
    private int mon_cnt = 1, tue_cnt = 1, wed_cnt = 1, thu_cnt = 1, fri_cnt = 1;

    public TableListAdapter(List<TableItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public TableListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weekday_add, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TableListAdapter.ViewHolder holder, int position) {
        final TableItems item = items.get(position);

        holder.date.setText(item.getDate());
        switch (String.valueOf(holder.date.getText())) {
            case "월요일":
                setBtn(mon_cnt, holder);
                setTimeButton(0, holder);
                holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mon_cnt = plus(mon_cnt, holder);
                    }
                });
                holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mon_cnt = minus(mon_cnt, holder);
                    }
                });
                break;
            case "화요일":
                setBtn(tue_cnt, holder);
                setTimeButton(1, holder);
                holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tue_cnt = plus(tue_cnt, holder);
                    }
                });
                holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tue_cnt = minus(tue_cnt, holder);
                    }
                });
                break;
            case "수요일":
                setBtn(wed_cnt, holder);
                setTimeButton(2, holder);
                holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wed_cnt = plus(wed_cnt, holder);
                    }
                });
                holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wed_cnt = minus(wed_cnt, holder);
                    }
                });
                break;
            case "목요일":
                setBtn(thu_cnt, holder);
                setTimeButton(3, holder);
                holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thu_cnt = plus(thu_cnt, holder);
                    }
                });
                holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        thu_cnt = minus(thu_cnt, holder);
                    }
                });
                break;
            case "금요일":
                setBtn(fri_cnt, holder);
                setTimeButton(4, holder);
                holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fri_cnt = plus(fri_cnt, holder);
                    }
                });
                holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fri_cnt = minus(fri_cnt, holder);
                    }
                });
                break;
        }
    }

    private void setBtn(int cnt, TableListAdapter.ViewHolder holder) {
        holder.btn1.setVisibility(View.VISIBLE);
        holder.btn2.setVisibility(View.VISIBLE);
        holder.btn3.setVisibility(View.VISIBLE);
        holder.btn4.setVisibility(View.VISIBLE);

        if (cnt == 1) {
            holder.plus_btn.setEnabled(true);
            holder.minus_btn.setEnabled(false);
            holder.btn2.setVisibility(View.GONE);
            holder.btn3.setVisibility(View.GONE);
            holder.btn4.setVisibility(View.GONE);
        } else if (cnt == 2) {
            holder.plus_btn.setEnabled(true);
            holder.minus_btn.setEnabled(true);
            holder.btn2.setVisibility(View.VISIBLE);
            holder.btn3.setVisibility(View.GONE);
            holder.btn4.setVisibility(View.GONE);
        } else if (cnt == 3) {
            holder.plus_btn.setEnabled(true);
            holder.minus_btn.setEnabled(true);
            holder.btn2.setVisibility(View.VISIBLE);
            holder.btn3.setVisibility(View.VISIBLE);
            holder.btn4.setVisibility(View.GONE);
        } else if (cnt == 4) {
            holder.plus_btn.setEnabled(false);
            holder.minus_btn.setEnabled(true);
        }
    }

    private void setTimeButton(final int s, final TableListAdapter.ViewHolder holder) {
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(context);
                customDialog.callFunction(s, holder.btn1);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(context);
                customDialog.callFunction(s, holder.btn2);
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(context);
                customDialog.callFunction(s, holder.btn3);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(context);
                customDialog.callFunction(s, holder.btn4);
            }
        });
    }

    private int plus(int cnt, TableListAdapter.ViewHolder holder) {
        cnt += 1;

        if (cnt == 2) {
            holder.btn2.setVisibility(View.VISIBLE);
            holder.minus_btn.setEnabled(true);
        } else if (cnt == 3)
            holder.btn3.setVisibility(View.VISIBLE);

        else if (cnt == 4) {
            holder.btn4.setVisibility(View.VISIBLE);
            holder.plus_btn.setEnabled(false);
            holder.minus_btn.setEnabled(true);
        }

        return cnt;
    }

    private int minus(int cnt, TableListAdapter.ViewHolder holder) {
        cnt -= 1;

        if (cnt == 1) {
            holder.plus_btn.setEnabled(true);
            holder.minus_btn.setEnabled(false);
            holder.btn2.setVisibility(View.GONE);
        } else if (cnt == 2)
            holder.btn3.setVisibility(View.GONE);
        else if (cnt == 3) {
            holder.btn4.setVisibility(View.GONE);
            holder.plus_btn.setEnabled(true);
        }

        return cnt;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        Button btn1;
        Button btn2;
        Button btn3;
        Button btn4;
        ImageButton plus_btn;
        ImageButton minus_btn;

        public ViewHolder(final View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.weekday_add_daytxt);
            btn1 = (Button) itemView.findViewById(R.id.weekday_add_btn1);
            btn2 = (Button) itemView.findViewById(R.id.weekday_add_btn2);
            btn3 = (Button) itemView.findViewById(R.id.weekday_add_btn3);
            btn4 = (Button) itemView.findViewById(R.id.weekday_add_btn4);
            plus_btn = (ImageButton) itemView.findViewById(R.id.weekday_add_plus_btn);
            minus_btn = (ImageButton) itemView.findViewById(R.id.weekday_add_minus_btn);
        }
    }
}
