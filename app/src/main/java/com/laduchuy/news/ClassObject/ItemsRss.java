package com.laduchuy.news.ClassObject;

import java.io.Serializable;


public class ItemsRss implements Serializable {

    private String title;
    private String description;
    private String link;
    private String urlImg;
    private String pubDate;

    public String getPubDate() {
        return pubDate;
    }

    public ItemsRss(String title, String description, String link, String urlImg, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.urlImg = urlImg;
        this.pubDate = pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public ItemsRss(String title, String description, String link, String urlImg) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.urlImg = urlImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
