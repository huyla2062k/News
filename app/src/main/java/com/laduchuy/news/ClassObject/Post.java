package com.laduchuy.news.ClassObject;

import java.io.Serializable;

public class Post implements Serializable {

    int post_id;
    String post_title;
    String post_desc;
    String post_thumb;
    String post_content;
    int category_id;

    public Post() {
    }

    public Post(int post_id, String post_title, String post_desc, String post_thumb, String post_content, int category_id) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_desc = post_desc;
        this.post_thumb = post_thumb;
        this.post_content = post_content;
        this.category_id = category_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public String getPost_thumb() {
        return post_thumb;
    }

    public void setPost_thumb(String post_thumb) {
        this.post_thumb = post_thumb;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
