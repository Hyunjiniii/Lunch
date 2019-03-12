package com.example.hyunjin.lunch.MainPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hyunjin.lunch.Meal.TableItems;
import com.example.hyunjin.lunch.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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
        if (position == 0) {
            holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mon_cnt += 1;

                    if (mon_cnt == 2)
                        holder.btn2.setVisibility(View.VISIBLE);
                    else if (mon_cnt == 3)
                        holder.btn3.setVisibility(View.VISIBLE);
                    else if (mon_cnt == 4) {
                        holder.btn4.setVisibility(View.VISIBLE);
                        holder.plus_btn.setEnabled(false);
                        holder.minus_btn.setEnabled(true);
                    }
                }
            });
            holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mon_cnt -= 1;

                    if (mon_cnt == 1) {
                        holder.plus_btn.setEnabled(true);
                        holder.minus_btn.setEnabled(false);
                        holder.btn2.setVisibility(View.GONE);
                    } else if (mon_cnt == 2)
                        holder.btn3.setVisibility(View.GONE);
                    else if (mon_cnt == 3)
                        holder.btn4.setVisibility(View.GONE);
                }
            });
        } else if (position == 1) {
            holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tue_cnt += 1;

                    if (tue_cnt == 2)
                        holder.btn2.setVisibility(View.VISIBLE);
                    else if (tue_cnt == 3)
                        holder.btn3.setVisibility(View.VISIBLE);
                    else if (tue_cnt == 4) {
                        holder.btn4.setVisibility(View.VISIBLE);
                        holder.plus_btn.setEnabled(false);
                        holder.minus_btn.setEnabled(true);
                    }
                }
            });
            holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tue_cnt -= 1;

                    if (tue_cnt == 1) {
                        holder.plus_btn.setEnabled(true);
                        holder.minus_btn.setEnabled(false);
                        holder.btn2.setVisibility(View.GONE);
                    } else if (tue_cnt == 2)
                        holder.btn3.setVisibility(View.GONE);
                    else if (tue_cnt == 3)
                        holder.btn4.setVisibility(View.GONE);
                }
            });
        }else if (position == 2) {
            holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wed_cnt += 1;

                    if (wed_cnt == 2)
                        holder.btn2.setVisibility(View.VISIBLE);
                    else if (wed_cnt == 3)
                        holder.btn3.setVisibility(View.VISIBLE);
                    else if (wed_cnt == 4) {
                        holder.btn4.setVisibility(View.VISIBLE);
                        holder.plus_btn.setEnabled(false);
                        holder.minus_btn.setEnabled(true);
                    }
                }
            });
            holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wed_cnt -= 1;

                    if (wed_cnt == 1) {
                        holder.plus_btn.setEnabled(true);
                        holder.minus_btn.setEnabled(false);
                        holder.btn2.setVisibility(View.GONE);
                    } else if (wed_cnt == 2)
                        holder.btn3.setVisibility(View.GONE);
                    else if (wed_cnt == 3)
                        holder.btn4.setVisibility(View.GONE);
                }
            });
        }else if (position == 3) {
            holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thu_cnt += 1;

                    if (thu_cnt == 2)
                        holder.btn2.setVisibility(View.VISIBLE);
                    else if (thu_cnt == 3)
                        holder.btn3.setVisibility(View.VISIBLE);
                    else if (thu_cnt == 4) {
                        holder.btn4.setVisibility(View.VISIBLE);
                        holder.plus_btn.setEnabled(false);
                        holder.minus_btn.setEnabled(true);
                    }
                }
            });
            holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thu_cnt -= 1;

                    if (thu_cnt == 1) {
                        holder.plus_btn.setEnabled(true);
                        holder.minus_btn.setEnabled(false);
                        holder.btn2.setVisibility(View.GONE);
                    } else if (thu_cnt == 2)
                        holder.btn3.setVisibility(View.GONE);
                    else if (thu_cnt == 3)
                        holder.btn4.setVisibility(View.GONE);
                }
            });
        }else if (position == 4) {
            holder.plus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fri_cnt += 1;

                    if (fri_cnt == 2)
                        holder.btn2.setVisibility(View.VISIBLE);
                    else if (fri_cnt == 3)
                        holder.btn3.setVisibility(View.VISIBLE);
                    else if (fri_cnt == 4) {
                        holder.btn4.setVisibility(View.VISIBLE);
                        holder.plus_btn.setEnabled(false);
                        holder.minus_btn.setEnabled(true);
                    }
                }
            });
            holder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fri_cnt -= 1;

                    if (fri_cnt == 1) {
                        holder.plus_btn.setEnabled(true);
                        holder.minus_btn.setEnabled(false);
                        holder.btn2.setVisibility(View.GONE);
                    } else if (fri_cnt == 2)
                        holder.btn3.setVisibility(View.GONE);
                    else if (fri_cnt == 3)
                        holder.btn4.setVisibility(View.GONE);
                }
            });
        }


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
