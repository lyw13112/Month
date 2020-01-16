package com.bawei.month.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.month.R;
import com.bawei.month.bean.FindCategoryBean;

import java.util.List;

/**
 * @author 刘云蔚
 * @createTime 2019/12/19 14:51
 * @description 首页左边RecyclerView的适配器
 */
public class HomeLeftAdapter extends RecyclerView.Adapter<HomeLeftAdapter.Holder> {

    private Context context;
    private List<FindCategoryBean.ResultBean> list;
    private int mColorPosition = 0;

    public HomeLeftAdapter(Context context, List<FindCategoryBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_home_left, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).getName());
        if (mColorPosition == position) {
            holder.textView.setTextColor(Color.RED);
        } else {
            holder.textView.setTextColor(Color.BLACK);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null) {
                    click.setIndex(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }



    private ItemClick click;

    public interface ItemClick {
        void setIndex(int position);
    }

    public void setItemClick(ItemClick click) {
        this.click = click;
    }

    public void setColorPosition(int position) {
        this.mColorPosition = position;
        notifyDataSetChanged();
    }
}
