package com.laduchuy.news.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.laduchuy.news.ClassObject.DanhMucBao;
import com.laduchuy.news.R;
import com.laduchuy.news.Utils.ScreenSlidePageFragment;
import com.laduchuy.news.Utils.StaticDataVNExpress;
import com.laduchuy.news.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        setSupportActionBar(binding.toolbarHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.pager.setAdapter(pagerAdapter);
        binding.tablayout.setupWithViewPager(binding.pager);
        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.setTabsFromPagerAdapter(pagerAdapter);

        binding.navigationview.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.menuDocTrucTuyen:
                                break;
                            case R.id.menuTinDaLuu:
                                Intent intent1 = new Intent(MainActivity.this, DocBaoOfflineActivity.class);
                                startActivity(intent1);
                                break;
                            case R.id.menuTuyChinh:
                                Intent intent = new Intent(getBaseContext(),SettingActivity.class);
                                startActivity(intent);
                                break;

                        }
                        binding.drawer.closeDrawers();
                        return true;
                    }
                });

    }



    public static ArrayList<DanhMucBao> getArrRSSVNExpress() {
        ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
        arrDanhMucBao.add(new DanhMucBao("Trang Chủ", StaticDataVNExpress.urlTrangChu));
        arrDanhMucBao.add(new DanhMucBao("Cộng Đồng",StaticDataVNExpress.urlCongDong));
        arrDanhMucBao.add(new DanhMucBao("Giải Trí",StaticDataVNExpress.urlGiaiTri));
        arrDanhMucBao.add(new DanhMucBao("Thời Sự",StaticDataVNExpress.urlThoiSu));
        arrDanhMucBao.add(new DanhMucBao("Giáo Dục",StaticDataVNExpress.urlGiaoDuc));
        arrDanhMucBao.add(new DanhMucBao("Du Lịch",StaticDataVNExpress.urlDuLich));
        arrDanhMucBao.add(new DanhMucBao("Khoa Học",StaticDataVNExpress.urlKhoaHoc));
        arrDanhMucBao.add(new DanhMucBao("Gia Đình",StaticDataVNExpress.urlGiaDinh));
        arrDanhMucBao.add(new DanhMucBao("Kinh Doanh",StaticDataVNExpress.urlKinhDoanh));
        arrDanhMucBao.add(new DanhMucBao("Pháp Luật",StaticDataVNExpress.urlPhapLuat));
        arrDanhMucBao.add(new DanhMucBao("Số Hóa",StaticDataVNExpress.urlSoHoa));
        arrDanhMucBao.add(new DanhMucBao("Startup",StaticDataVNExpress.urlStartUp));
        arrDanhMucBao.add(new DanhMucBao("Sức Khỏe",StaticDataVNExpress.urlSucKhoe));
        arrDanhMucBao.add(new DanhMucBao("Tâm Sự",StaticDataVNExpress.urlTamSu));
        arrDanhMucBao.add(new DanhMucBao("Thế Giới",StaticDataVNExpress.urlTheGioi));
        arrDanhMucBao.add(new DanhMucBao("Thể Thao",StaticDataVNExpress.urlTheThao));
        arrDanhMucBao.add(new DanhMucBao("Xe",StaticDataVNExpress.urlXe));
        arrDanhMucBao.add(new DanhMucBao("Cười",StaticDataVNExpress.urlCuoi));
        return arrDanhMucBao;
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new ScreenSlidePageFragment().create(position,getArrRSSVNExpress().get(position).getUrlDanhMuc());
        }

        @Override
        public int getCount() {
            return getArrRSSVNExpress().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getArrRSSVNExpress().get(position).getTendanhmuc();
        }
    }
}