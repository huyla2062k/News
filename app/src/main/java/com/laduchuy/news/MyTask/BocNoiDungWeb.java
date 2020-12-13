package com.laduchuy.news.MyTask;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class BocNoiDungWeb extends AsyncTask<String, Void, String> {

    public static String H1 = "h1";
    public static String H2="h2";
    public  static  String ARTICLE="article";
    @Override
    protected String doInBackground(String... strings) {

        Document document = null;
        String url = strings[0];

        Elements title = null;
        Elements description = null;
        Elements conten_detail = null;

        StringBuffer noidung = new StringBuffer();

        try {

            document = Jsoup.connect(url).get();


            title = document.select(H1);
            description = document.select(H2);
            conten_detail = document.select(ARTICLE);

            noidung.append(title);
            noidung.append(description);
            noidung.append(conten_detail);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return noidung.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
