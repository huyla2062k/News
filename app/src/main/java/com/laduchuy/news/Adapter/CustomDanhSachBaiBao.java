package com.laduchuy.news.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laduchuy.news.ClassObject.ItemsRss;
import com.laduchuy.news.R;


import java.util.List;


public class CustomDanhSachBaiBao extends ArrayAdapter<ItemsRss> {


    @NonNull
    Context context;
    int resource;
    @NonNull
    List<ItemsRss> objects;


    public CustomDanhSachBaiBao(@NonNull Context context, int resource, @NonNull List<ItemsRss> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_dsbaibao, parent, false);
            viewHolder.anhbaibao = convertView.findViewById(R.id.img_anhbaibao);
            viewHolder.tieudebaibao = convertView.findViewById(R.id.tv_tieudebaibao);
            viewHolder.tvDes = convertView.findViewById(R.id.tvDes);
            viewHolder.tvTime = convertView.findViewById(R.id.tvTime);
            viewHolder.imgHotNew = convertView.findViewById(R.id.imgHotNews);
            viewHolder.tvHotNew = convertView.findViewById(R.id.tvHotTitle);
            viewHolder.rvHotNews = convertView.findViewById(R.id.itemHotNew);
            viewHolder.lvNews = convertView.findViewById(R.id.itemNews);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position==0){
            ItemsRss itemsRss = objects.get(position);
            Glide.with(context).load(itemsRss.getUrlImg()).into(viewHolder.imgHotNew);
            viewHolder.tvHotNew.setText(itemsRss.getTitle());
            viewHolder.lvNews.setVisibility(View.GONE);
        }else {
            ItemsRss itemsRss = objects.get(position);
            viewHolder.anhbaibao.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(itemsRss.getUrlImg()).into(viewHolder.anhbaibao);
            viewHolder.tieudebaibao.setText(itemsRss.getTitle());
            viewHolder.tvTime.setText(itemsRss.getPubDate());
            viewHolder.tvDes.setText(itemsRss.getDescription());
            viewHolder.rvHotNews.setVisibility(View.GONE);
        }


        return convertView;
    }

    public class ViewHolder {
        RelativeLayout rvHotNews;
        LinearLayout lvNews;
        ImageView anhbaibao;
        TextView tieudebaibao;
        ImageView imgHotNew;
        TextView tvHotNew;
        TextView tvDes;
        TextView tvTime;
    }

}
