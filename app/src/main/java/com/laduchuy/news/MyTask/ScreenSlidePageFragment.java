package com.laduchuy.news.MyTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.laduchuy.news.Activity.MainActivity;
import com.laduchuy.news.Activity.NoiDungBao;
import com.laduchuy.news.Adapter.CustomDanhSachBaiBao;
import com.laduchuy.news.ClassObject.DanhMucBao;
import com.laduchuy.news.ClassObject.ItemsRss;
import com.laduchuy.news.ClassObject.OfflineRSSItem;
import com.laduchuy.news.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ScreenSlidePageFragment extends Fragment {

    public static final String ARG_PAGE = "page";
    public static final String ARG_RSS = "rss";
    ListView lvDSBaiBao;
    ImageView imgHotNew;
    TextView tvHotTitle;
    ArrayList<DanhMucBao> arrRSS = new ArrayList<>();
    ArrayList<ItemsRss> itemsRsses = new ArrayList<>();

    DocRss docRss = new DocRss();
     OfflineRSSItem offlineRSSItem;

    public static ScreenSlidePageFragment create(int numbPage, String urlRSS) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE, numbPage);
        bundle.putString(ARG_RSS, urlRSS);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fr_screenslide_page, container, false);

        lvDSBaiBao = rootView.findViewById(R.id.lvDSBaiBao);
        imgHotNew = rootView.findViewById(R.id.imgHotNews);
        tvHotTitle = rootView.findViewById(R.id.tvHotTitle);

        String url = getArguments().getString(ARG_RSS);
        try {
            itemsRsses = docRss.execute(url).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final CustomDanhSachBaiBao customDanhSachBaiBao = new CustomDanhSachBaiBao(getContext(), R.layout.item_listview_dsbaibao, itemsRsses);
        if (itemsRsses == null) {
            Toast.makeText(getContext(), "Ko co dữ liệu", Toast.LENGTH_SHORT).show();
        } else {

//            imgHotNew.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    BocNoiDungWeb bocNoiDungWeb = new BocNoiDungWeb();
//                    bocNoiDungWeb.execute(customDanhSachBaiBao.getItem(0).getLink());
//                    String noidunglayduoc = null;
//                    try {
//                        noidunglayduoc = bocNoiDungWeb.get();
//                        offlineRSSItem = new OfflineRSSItem(customDanhSachBaiBao.getItem(0).getTitle(), customDanhSachBaiBao.getItem(0).getDescription(), noidunglayduoc, customDanhSachBaiBao.getItem(0).getUrlImg());
//                        Intent intent = new Intent(getContext(), NoiDungBao.class);
//                        intent.putExtra("OfflineRSSItem", offlineRSSItem);
//                        intent.putExtra("ndBaiBao", noidunglayduoc);
//                        intent.putExtra("URL", customDanhSachBaiBao.getItem(0).getLink());
//                        startActivity(intent);
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });

            lvDSBaiBao.setAdapter(customDanhSachBaiBao);
        }


        lvDSBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BocNoiDungWeb bocNoiDungWeb = new BocNoiDungWeb();
                bocNoiDungWeb.execute(itemsRsses.get(i).getLink());

                try {
                    String noidunglayduoc = bocNoiDungWeb.get();
                    offlineRSSItem = new OfflineRSSItem(itemsRsses.get(i).getTitle(), itemsRsses.get(i).getDescription(), noidunglayduoc, itemsRsses.get(i).getUrlImg());
                    Intent intent = new Intent(getContext(), NoiDungBao.class);
                    intent.putExtra("OfflineRSSItem", offlineRSSItem);
                    intent.putExtra("ndBaiBao", noidunglayduoc);
                    intent.putExtra("URL", itemsRsses.get(i).getLink());
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrRSS = MainActivity.getArrRSSVNExpress();
    }

}
