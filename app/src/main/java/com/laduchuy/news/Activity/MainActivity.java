package com.laduchuy.news.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.laduchuy.news.ClassObject.DanhMucBao;
import com.laduchuy.news.R;
import com.laduchuy.news.Utils.ScreenSlidePageFragment;
import com.laduchuy.news.Utils.StaticDataVNExpress;
import com.laduchuy.news.Utils.Utils;
import com.laduchuy.news.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String MAIN = "Trang Chủ";
    public static String Community = "Cộng Đồng";
    public static String Entertainment = "Giải Trí";
    public static String The_Times = "Thời Sự";
    public static String Education = "Giáo dục";
    public static String Travel = "Du Lịch";
    public static String Science = "Khoa Học";
    public static String Family = "Gia Đình";
    public static String Business = "Kinh Doanh";
    public static String Law = "Pháp Luật";
    public static String Digitalization = "Số Hóa";
    public static String Startup = "Startup";
    public static String Health = "Sức Khỏe";
    public static String Confiding = "Tâm sự";
    public static String World = "Thế Giới";
    public static String Sports = "Thể Thao";
    public static String Car = "Xe";
    public static String Laugh = "Cười";
    public static String Dia_TITLE = "Không có kêt nối Interner";
    public static String Dia_Mess = "Bạn chuyển đến đọc tin đã lưu không!";
    public static String Dia_OK = "Đồng ý";
    public static String Dia_CANCLE = "Không";


    ActivityMainBinding binding;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());


        if (Utils.isNetworkAvailable(getBaseContext())) {
            setSupportActionBar(binding.toolbarHome);
            binding.toolbarHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

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

                            switch (menuItem.getItemId()) {
                                case R.id.menuDocTrucTuyen:
                                    break;
                                case R.id.menuTinDaLuu:
                                    Intent intent1 = new Intent(MainActivity.this, DocBaoOfflineActivity.class);
                                    startActivity(intent1);
                                    break;
                                case R.id.menuTuyChinh:
                                    Intent intent = new Intent(getBaseContext(), SettingActivity.class);
                                    startActivityForResult(intent, 122);
                                    break;

                            }
                            binding.drawer.closeDrawers();
                            return true;
                        }
                    });
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(Dia_TITLE);
            builder.setMessage(Dia_Mess);
            builder.setPositiveButton(Dia_OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent1 = new Intent(MainActivity.this, DocBaoOfflineActivity.class);
                    startActivity(intent1);
                }
            });
            builder.setNegativeButton(Dia_CANCLE, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(1);
                }
            });
            builder.show();
        }


    }


    public static ArrayList<DanhMucBao> getArrRSSVNExpress() {
        ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
        arrDanhMucBao.add(new DanhMucBao(MAIN, StaticDataVNExpress.urlTrangChu));
        arrDanhMucBao.add(new DanhMucBao(Community, StaticDataVNExpress.urlCongDong));
        arrDanhMucBao.add(new DanhMucBao(Entertainment, StaticDataVNExpress.urlGiaiTri));
        arrDanhMucBao.add(new DanhMucBao(The_Times, StaticDataVNExpress.urlThoiSu));
        arrDanhMucBao.add(new DanhMucBao(Education, StaticDataVNExpress.urlGiaoDuc));
        arrDanhMucBao.add(new DanhMucBao(Travel, StaticDataVNExpress.urlDuLich));
        arrDanhMucBao.add(new DanhMucBao(Science, StaticDataVNExpress.urlKhoaHoc));
        arrDanhMucBao.add(new DanhMucBao(Family, StaticDataVNExpress.urlGiaDinh));
        arrDanhMucBao.add(new DanhMucBao(Business, StaticDataVNExpress.urlKinhDoanh));
        arrDanhMucBao.add(new DanhMucBao(Law, StaticDataVNExpress.urlPhapLuat));
        arrDanhMucBao.add(new DanhMucBao(Digitalization, StaticDataVNExpress.urlSoHoa));
        arrDanhMucBao.add(new DanhMucBao(Startup, StaticDataVNExpress.urlStartUp));
        arrDanhMucBao.add(new DanhMucBao(Health, StaticDataVNExpress.urlSucKhoe));
        arrDanhMucBao.add(new DanhMucBao(Confiding, StaticDataVNExpress.urlTamSu));
        arrDanhMucBao.add(new DanhMucBao(World, StaticDataVNExpress.urlTheGioi));
        arrDanhMucBao.add(new DanhMucBao(Sports, StaticDataVNExpress.urlTheThao));
        arrDanhMucBao.add(new DanhMucBao(Car, StaticDataVNExpress.urlXe));
        arrDanhMucBao.add(new DanhMucBao(Laugh, StaticDataVNExpress.urlCuoi));
        return arrDanhMucBao;
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new ScreenSlidePageFragment().create(position, getArrRSSVNExpress().get(position).getUrlDanhMuc());
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