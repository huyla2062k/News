package com.laduchuy.news.Utils;

import android.app.Activity;
import android.content.Intent;

import com.laduchuy.news.R;


public class Utils {

    private static int sTheme;
    public final static int THEME_DEFAULT = 0;
    public final static int THEME_BLACK = 1;
    public static int size=100;
    public  static boolean darkmode;

    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        onActivityCreateSetTheme(activity);
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));

    }

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

}
