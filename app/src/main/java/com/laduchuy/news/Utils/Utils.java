package com.laduchuy.news.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.laduchuy.news.R;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static androidx.core.content.ContextCompat.getSystemService;


public class Utils {

    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_BLACK = 1;
    public static int size=100;
    public  static boolean darkmode = false;

//    public static void changeToTheme(Activity activity, int theme)
//    {
//        sTheme = theme;
//        onActivityCreateSetTheme(activity);
//        activity.finish();
//        activity.startActivity(new Intent(activity, activity.getClass()));
//
//    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_DEFAULT:
                activity.setTheme(R.style.AppTheme);
                break;
            case THEME_BLACK:
                activity.setTheme(R.style.BlackTheme);
                break;
        }
    }
//    public static boolean isNetworkAvailable(Context context) {
//        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
//        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
//    }
//
//    public static boolean isInternetAvailable(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) { // connected to the internet
                // connected to the mobile provider's data plan
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    return true;
                } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }

    public static String translate(String main){
        if (main.equals("Mild")) return "Ấm áp";
        else if (main.equals("Dry")) return "Hanh khô";
        else if (main.equals("Clear")) return "Trời quang";
        else if (main.equals("Windy")) return "Nhiều gió";
        else if (main.equals("Gloomy")) return "Trời ảm đạm";
        else if (main.equals("Cloudy")) return "Nhiều mây";
        else if (main.equals("Overcast")) return "Trời âm u";
        else if (main.equals("Foggy")) return "Sương mù";
        else if (main.equals("Haze")) return "Sương mù";
        else if (main.equals("Rain")) return "Mưa";
        else return main;
    }



}


