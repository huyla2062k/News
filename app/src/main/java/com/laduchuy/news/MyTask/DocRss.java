package com.laduchuy.news.MyTask;

import android.os.AsyncTask;

import com.laduchuy.news.ClassObject.ItemsRss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class DocRss extends AsyncTask<String, Void, ArrayList<ItemsRss>> {
    @Override
    protected ArrayList<ItemsRss> doInBackground(String... strings) {

        String url = strings[0];
        ArrayList<ItemsRss> items = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();

            Elements elements = document.select("item");
            for (Element element: elements){
                String title = element.select("title").text();

                String itemDescription = element.select("description").text();

                Document docDescription = Jsoup.parse(itemDescription);

                String link = docDescription.select("a").attr("href");
                String urlImg = docDescription.select("img ").attr("src");
                String description = docDescription.text();

                items.add(new ItemsRss(title,description,link,urlImg));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemsRss> itemsRsses) {
        super.onPostExecute(itemsRsses);
    }
}
