package com.bawei.month.adapter;

import android.content.Context;
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
 * @createTime 2019/12/20 11:02
 * @description
 */
public class HomeRightAdapter extends RecyclerView.Adapter<HomeRightAdapter.Holder> {

    private Context context;
    private List<FindCategoryBean.ResultBean.SecondCategoryVoBean> list;

    public HomeRightAdapter(Context context, List<FindCategoryBean.ResultBean.SecondCategoryVoBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(context, R.layout.item_home_right, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView.setText(list.get(position).getName());
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
}
