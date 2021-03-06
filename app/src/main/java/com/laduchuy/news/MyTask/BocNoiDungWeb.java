package com.laduchuy.news.MyTask;

import android.os.AsyncTask;

import com.laduchuy.news.Utils.Detail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class BocNoiDungWeb extends AsyncTask<String, Void, String> {


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


            title = document.select(Detail.H1);
            description = document.select(Detail.H2);
            conten_detail = document.select(Detail.ARTICLE);

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
