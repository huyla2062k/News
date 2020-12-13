package com.laduchuy.news.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import com.laduchuy.news.ClassObject.OfflineRSSItem;
import com.laduchuy.news.Database.DBOfflineRSSItem;
import com.laduchuy.news.R;
import com.laduchuy.news.Utils.Utils;
import com.laduchuy.news.databinding.NoidungbaoActivityBinding;

public class NoiDungBao extends AppCompatActivity {
    NoidungbaoActivityBinding binding;


    private OfflineRSSItem offlineRSSItem;

    DBOfflineRSSItem itemRssController;
    ShareLinkContent shareLinkContent;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.noidungbao_activity);


        //shareDialog = new ShareDialog(this);
        Actions();
        itemRssController = new DBOfflineRSSItem(NoiDungBao.this);
        offlineRSSItem = (OfflineRSSItem) getIntent().getSerializableExtra("OfflineRSSItem");
//        Utils.changeToTheme(this,1);


    }


    private void Actions() {
        Intent intent = getIntent();
        String noidunglayduoc = intent.getStringExtra("ndBaiBao");
        final String url = intent.getStringExtra("URL");
        if (Utils.darkmode == true) {
            binding.webviewbaibao.setBackgroundColor(Color.GRAY);
            binding.webviewbaibao.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;}  </style></head><body>" + noidunglayduoc + "</body></html>", "text/html", "UTF-8");
        } else {
            binding.webviewbaibao.setBackgroundColor(Color.WHITE);
            binding.webviewbaibao.loadData("<html><head><style>img{display: inline; height: auto; max-width: 100%;} </style></head><body>" + noidunglayduoc + "</body></html>", "text/html", "UTF-8");
        }

        binding.webviewbaibao.setVisibility(View.VISIBLE);
        binding.webviewbaibao.getSettings().setJavaScriptEnabled(true);
        binding.webviewbaibao.getSettings().setTextZoom(Utils.size);
//        binding.webviewbaibao.setBackgroundColor(Color.BLACK);


        binding.webviewbaibao.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Toast.makeText(NoiDungBao.this, "Đang tải dữ liệu", Toast.LENGTH_SHORT).show();
                binding.progressbar.setVisibility(View.VISIBLE);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressbar.setVisibility(View.GONE);
                binding.webviewbaibao.setVisibility(View.VISIBLE);
                Toast.makeText(NoiDungBao.this, "Tải dữ liệu thành công", Toast.LENGTH_SHORT).show();
                binding.progressbar.setVisibility(View.GONE);
            }
        });

        binding.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NoiDungBao.this);
                builder.setTitle("Bạn muốn lưu tin?");
                builder.setMessage("Tin sẽ được lưu lại để đọc khi không có mạng");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long ok = itemRssController.Insert(offlineRSSItem);
                        if (ok > 0) {
                            Toast.makeText(NoiDungBao.this, "Đã lưu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create().show();
            }
        });

//        binding.imgShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ShareDialog.canShow(ShareLinkContent.class)){
//                    shareLinkContent = new ShareLinkContent.Builder()
//                            .setContentUrl(Uri.parse(url)).build();
//                }
//                shareDialog.show(shareLinkContent);
//            }
//        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
