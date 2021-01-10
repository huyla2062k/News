package com.laduchuy.news.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.laduchuy.news.ClassObject.DanhMucBao;
import com.laduchuy.news.MyTask.DialogFragment;
import com.laduchuy.news.R;
import com.laduchuy.news.MyTask.ScreenSlidePageFragment;
import com.laduchuy.news.Utils.ApiUtils;
import com.laduchuy.news.Utils.Detail;
import com.laduchuy.news.Utils.SOService;
import com.laduchuy.news.Utils.StaticDataVNExpress;
import com.laduchuy.news.Utils.Utils;
import com.laduchuy.news.WeatherObject.Main;
import com.laduchuy.news.WeatherObject.SOAnswersResponse;
import com.laduchuy.news.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    ActivityMainBinding binding;
    PagerAdapter pagerAdapter;
    SOService soService;
    LocationManager locationManager;
    String provider;
    String longtitude;
    String lattitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        checkLocationPermission();
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
//            new ReadWebAPI().execute();
        Tovuti.from(this).monitor(new Monitor.ConnectivityListener() {
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast) {
                if (isConnected && isFast){
                    Action();
                }
                else {
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
        });
//        if (Utils.checkConnection(getBaseContext())) {
//           Action();
//        } else {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle(Detail.Dia_TITLE);
//            builder.setMessage(Detail.Dia_Mess);
//            builder.setPositiveButton(Detail.Dia_OK, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Intent intent1 = new Intent(MainActivity.this, DocBaoOfflineActivity.class);
//                    startActivity(intent1);
//                }
//            });
//            builder.setNegativeButton(Detail.Dia_CANCLE, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    System.exit(1);
//                }
//            });
//            builder.show();
//        }


    }

    private void Action() {
        setSupportActionBar(binding.toolbarHome);
        binding.toolbarHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        setSupportActionBar(binding.toolbarHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        soService = ApiUtils.getSOService();

        loadAnswers();

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                longtitude = location.getLongitude() + "";
                longtitude = longtitude.substring(0, 5);
                lattitude = location.getLongitude() + "";
                lattitude = lattitude.substring(0, 5);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


    }


    public static ArrayList<DanhMucBao> getArrRSSVNExpress() {
        ArrayList<DanhMucBao> arrDanhMucBao = new ArrayList<>();
        arrDanhMucBao.add(new DanhMucBao(Detail.Main, StaticDataVNExpress.urlTrangChu));
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

    //    public class ReadWebAPI extends AsyncTask<Void, Void, String> {
//
//        String urlApi = "http://api.openweathermap.org/data/2.5/weather?q=Hanoi&appid=559e9a92d09586b2faced211d05bb1dd";
//        String result = "";
//
//        @Override
//        protected String doInBackground(Void... voids) {
//
//
//            try {
//                URL url = new URL(urlApi);
//                URLConnection connection = url.openConnection();
//                InputStream is = connection.getInputStream();
//                int byteChacracter;
//                while ((byteChacracter = is.read()) != -1) {
//
//                    result += (char) byteChacracter;
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            getJson(result);
//        }
//
//        private void getJson(String json) {
//            try {
//
//                JSONObject jsonObject = new JSONObject(json);
//                JSONObject main = jsonObject.getJSONObject("main");
//                double temp = main.getDouble("temp");
//                int tempC = (int) (temp-273.15);
//                binding.tvTemple.setText(String.valueOf(tempC));
//                JSONArray jsonArray = jsonObject.getJSONArray("weather");
//                JSONObject des = jsonArray.getJSONObject(0);
//                String mainWeather = des.getString("main");
//                String desWeather = des.getString("description");
//                final String description = mainWeather + ": " + desWeather;
//                binding.tvTemple.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getBaseContext(), description, Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
    private void loadAnswers() {
        soService.getAnswers("tagged").enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if (response.isSuccessful()) {
                    final int tempC = (int) (response.body().getMain().getTemp() - 273.15);
                    int tempMax = (int) (response.body().getMain().getTempMax() - 273.15);
                    int tempMin = (int) (response.body().getMain().getTempMin() - 273.15);
                    binding.tvTemple.setText(String.valueOf(tempC));
                    final String main = Utils.translate(response.body().getWeather().get(0).getMain());
                    final String des;
                    if (tempC == tempMax && tempC == tempMin) {

                        des = "Nhiệt độ cao nhất " + tempMax + "°C";
                    } else if (tempC == tempMax) {
                        des = "Nhiệt độ có thể giảm xuống " + tempMin + "°C";
                    } else if (tempC == tempMin) {
                        des = "Nhiệt độ cao nhất " + tempMax + "°C";
                    } else {
                        des = "Nhiệt độ cao nhất " + tempMax + "°C";
                    }

                    int sp = (int) (response.body().getWind().getSpeed() * 3.6);
                    final String speed = " " + sp + " km/h";

                    double vi = response.body().getVisibility() / 1000;
                    final String visibility = " " + vi + " km";
                    final String humidity = " " + response.body().getMain().getHumidity() + "%";
                    final String pressure = " " + response.body().getMain().getPressure() + "mb";

                    binding.tvTemple.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DialogFragment dialogFragment = new DialogFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Detail.TEMPC, String.valueOf(tempC) + "°C");
                            bundle.putString(Detail.MAIN, main);
                            bundle.putString(Detail.DES, des);
                            bundle.putString(Detail.SPEED, speed);
                            bundle.putString(Detail.VISIBILITY, visibility);
                            bundle.putString(Detail.HUMIDITY, humidity);
                            bundle.putString(Detail.PRESSURE, pressure);
                            dialogFragment.setArguments(bundle);

                            dialogFragment.show(MainActivity.this.getSupportFragmentManager(), "dialogfragment");
                        }
                    });
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }

            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                Log.d("MainActivity", "error loading from API");
            }
        });


    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("ACCESS_FINE_LOCATION")
                        .setMessage("Cho phép ứng dụng truy cập vào vị trí thiết bị của bạn")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


}