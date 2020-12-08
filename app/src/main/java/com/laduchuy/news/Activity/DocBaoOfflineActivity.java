package com.laduchuy.news.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.navigation.NavigationView;
import com.laduchuy.news.Adapter.AdapterListBaiBaoOffline;
import com.laduchuy.news.ClassObject.OfflineRSSItem;
import com.laduchuy.news.Database.DBOfflineRSSItem;
import com.laduchuy.news.Database.DBPosts;
import com.laduchuy.news.R;
import com.laduchuy.news.databinding.ActivityDocbaoofflineBinding;


import java.util.ArrayList;
import java.util.List;


public class DocBaoOfflineActivity extends AppCompatActivity {
    ActivityDocbaoofflineBinding binding;

    DBOfflineRSSItem dbOfflineRSSItem;
    List<OfflineRSSItem> offlineRSSItems = new ArrayList<>();
    AdapterListBaiBaoOffline adapterListBaiBaoOffline;

    DBPosts dbPosts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docbaooffline);

        setSupportActionBar(binding.toolbarTrangchu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbarTrangchu.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        binding.toolbarTrangchu.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.drawer.openDrawer(GravityCompat.START);
                    }
                }
        );

        binding.navigationview.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.menuTinDaLuu:
                                break;
                            case R.id.menuTuyChinh:
                                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                                startActivity(intent1);
                                break;
                        }
                        binding.drawer.closeDrawers();
                        return true;
                    }
                });
        registerForContextMenu(binding.lvDSBaiBaoOffLine);

        dbOfflineRSSItem = new DBOfflineRSSItem(DocBaoOfflineActivity.this);
        dbPosts = new DBPosts(DocBaoOfflineActivity.this);

        offlineRSSItems = dbOfflineRSSItem.getAlLOffLineItemRss();



        Toast.makeText(this,offlineRSSItems.size() + "", Toast.LENGTH_SHORT).show();
        adapterListBaiBaoOffline = new AdapterListBaiBaoOffline(DocBaoOfflineActivity.this,R.layout.item_listview_dsbaibao,offlineRSSItems);
        binding.lvDSBaiBaoOffLine.setAdapter(adapterListBaiBaoOffline);

        binding.lvDSBaiBaoOffLine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DocBaoOfflineActivity.this,NoiDungBao.class);
                intent.putExtra("ndBaiBao",offlineRSSItems.get(i).getContent());
                startActivity(intent);
            }
        });


        binding.lvDSBaiBaoOffLine.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int positon = i;

                Toast.makeText(DocBaoOfflineActivity.this, "Xóa", Toast.LENGTH_SHORT).show();

                final AlertDialog.Builder builder = new AlertDialog.Builder(DocBaoOfflineActivity.this);
                builder.setTitle("Alert!!");
                builder.setMessage("Bạn có muốn xóa!!!");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbOfflineRSSItem.delete(offlineRSSItems.get(positon));
                        offlineRSSItems.clear();
                        offlineRSSItems.addAll(dbOfflineRSSItem.getAlLOffLineItemRss());
                        adapterListBaiBaoOffline.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });

    }



}
