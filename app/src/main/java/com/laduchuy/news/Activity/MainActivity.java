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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.laduchuy.news.ClassObject.DanhMucBao;
import com.laduchuy.news.R;
import com.laduchuy.news.MyTask.ScreenSlidePageFragment;
import com.laduchuy.news.Utils.Detail;
import com.laduchuy.news.Utils.StaticDataVNExpress;
import com.laduchuy.news.Utils.Utils;
import com.laduchuy.news.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());


        if (Utils.isInternetAvailable(getBaseContext())) {
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
            new ReadWebAPI().execute();


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(Detail.Dia_TITLE);
            builder.setMessage(Detail.Dia_Mess);
            builder.setPositiveButton(Detail.Dia_OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent1 = new Intent(MainActivity.this, DocBaoOfflineActivity.class);
                    startActivity(intent1);
                }
            });
            builder.setNegativeButton(Detail.Dia_CANCLE, new DialogInterface.OnClickListener() {
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
        arrDanhMucBao.add(new DanhMucBao(Detail.MAIN, StaticDataVNExpress.urlTrangChu));
        arrDanhMucBao.add(new DanhMucBao(Detail.Community, StaticDataVNExpress.urlCongDong));
        arrDanhMucBao.add(new DanhMucBao(Detail.Entertainment, StaticDataVNExpress.urlGiaiTri));
        arrDanhMucBao.add(new DanhMucBao(Detail.The_Times, StaticDataVNExpress.urlThoiSu));
        arrDanhMucBao.add(new DanhMucBao(Detail.Education, StaticDataVNExpress.urlGiaoDuc));
        arrDanhMucBao.add(new DanhMucBao(Detail.Travel, StaticDataVNExpress.urlDuLich));
        arrDanhMucBao.add(new DanhMucBao(Detail.Science, StaticDataVNExpress.urlKhoaHoc));
        arrDanhMucBao.add(new DanhMucBao(Detail.Family, StaticDataVNExpress.urlGiaDinh));
        arrDanhMucBao.add(new DanhMucBao(Detail.Business, StaticDataVNExpress.urlKinhDoanh));
        arrDanhMucBao.add(new DanhMucBao(Detail.Law, StaticDataVNExpress.urlPhapLuat));
        arrDanhMucBao.add(new DanhMucBao(Detail.Digitalization, StaticDataVNExpress.urlSoHoa));
        arrDanhMucBao.add(new DanhMucBao(Detail.Startup, StaticDataVNExpress.urlStartUp));
        arrDanhMucBao.add(new DanhMucBao(Detail.Health, StaticDataVNExpress.urlSucKhoe));
        arrDanhMucBao.add(new DanhMucBao(Detail.Confiding, StaticDataVNExpress.urlTamSu));
        arrDanhMucBao.add(new DanhMucBao(Detail.World, StaticDataVNExpress.urlTheGioi));
        arrDanhMucBao.add(new DanhMucBao(Detail.Sports, StaticDataVNExpress.urlTheThao));
        arrDanhMucBao.add(new DanhMucBao(Detail.Car, StaticDataVNExpress.urlXe));
        arrDanhMucBao.add(new DanhMucBao(Detail.Laugh, StaticDataVNExpress.urlCuoi));
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

    public class ReadWebAPI extends AsyncTask<Void, Void, String> {

        String urlApi = "http://api.openweathermap.org/data/2.5/weather?q=Hanoi&appid=559e9a92d09586b2faced211d05bb1dd";
        String result = "";

        @Override
        protected String doInBackground(Void... voids) {


            try {
                URL url = new URL(urlApi);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteChacracter;
                while ((byteChacracter = is.read()) != -1) {

                    result += (char) byteChacracter;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getJson(result);
        }

        private void getJson(String json) {
            try {

                JSONObject jsonObject = new JSONObject(json);
                JSONObject main = jsonObject.getJSONObject("main");
                double temp = main.getDouble("temp");
                int tempC = (int) (temp-273.15);
                binding.tvTemple.setText(String.valueOf(tempC));
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                JSONObject des = jsonArray.getJSONObject(0);
                String mainWeather = des.getString("main");
                String desWeather = des.getString("description");
                final String description = mainWeather + ": " + desWeather;
                binding.tvTemple.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getBaseContext(), description, Toast.LENGTH_LONG).show();
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}