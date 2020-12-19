package com.laduchuy.news.MyTask;

import android.os.AsyncTask;

import com.laduchuy.news.ClassObject.ItemsRss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DocRss extends AsyncTask<String, Void, ArrayList<ItemsRss>> {

    public static String ITEM = "item";
    public static String TITLE = "title";
    public static String DES = "description";
    public static String A = "a";
    public static String HREF = "href";
    public static String IMG = "img";
    public static String SRC = "src";

    @Override
    protected ArrayList<ItemsRss> doInBackground(String... strings) {

        String url = strings[0];
        ArrayList<ItemsRss> items = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            String dateformat="EEE, dd MMM yyyy HH:mm:ss Z";
            String datformatv2 = "EEE, dd/MM/yyyy HH:mm";
            SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
            SimpleDateFormat sdfv2 = new SimpleDateFormat(datformatv2);
            Elements elements = document.select(ITEM);
            for (Element element : elements) {
                String title = element.select(TITLE).text();
                String pubDate = element.select("pubDate").text();
                String date = pubDate.substring(0,pubDate.lastIndexOf(" "));
//                Date date = sdf.parse(pubDate);
//                String update = sdfv2.format(date);




                String itemDescription = element.select(DES).text();

                Document docDescription = Jsoup.parse(itemDescription);

                String link = docDescription.select(A).attr(HREF);
                String urlImg = docDescription.select(IMG).attr(SRC);
                String description = docDescription.text();
                items.add(new ItemsRss(title, description, link, urlImg,date));


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemsRss> itemsRsses) {
        super.onPostExecute(itemsRsses);
    }
}
