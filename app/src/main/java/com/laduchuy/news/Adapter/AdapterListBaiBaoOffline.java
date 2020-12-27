package com.laduchuy.news.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.laduchuy.news.ClassObject.OfflineRSSItem;
import com.laduchuy.news.R;


import java.util.List;


public class AdapterListBaiBaoOffline extends ArrayAdapter<OfflineRSSItem> {

    @NonNull
    Context context;
    int resource;
    @NonNull
    List<OfflineRSSItem> objects;

    public AdapterListBaiBaoOffline(@NonNull Context context, int resource, @NonNull List<OfflineRSSItem> objects) {
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

            convertView = LayoutInflater.from(context).inflate(R.layout.item_dsbaibao, parent, false);
            viewHolder.anhbaibao = convertView.findViewById(R.id.imgAvarta);
            viewHolder.tieudebaibao = convertView.findViewById(R.id.tvTitle);
viewHolder.tvDes = convertView.findViewById(R.id.tvDes);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (AdapterListBaiBaoOffline.ViewHolder) convertView.getTag();
        }


        OfflineRSSItem itemsRss = objects.get(position);
        viewHolder.anhbaibao.setScaleType(ImageView.ScaleType.FIT_XY);

        viewHolder.tvDes.setText(itemsRss.getDescription());
        if (itemsRss.getUrlImg() != null){
            Glide.with(context).load(itemsRss.getUrlImg()).into(viewHolder.anhbaibao);
        }

        viewHolder.tieudebaibao.setText(itemsRss.getTitle());

        return convertView;
    }

    public class ViewHolder {
        ImageView anhbaibao;
        TextView tieudebaibao;

        TextView tvDes;
        TextView tvTime;
    }

}