package com.laduchuy.news.ClassObject;

import java.io.Serializable;



public class DanhMucBao implements Serializable {

    String tendanhmuc;
    String urlDanhMuc;

    public DanhMucBao(String tendanhmuc, String urlDanhMuc) {
        this.tendanhmuc = tendanhmuc;
        this.urlDanhMuc = urlDanhMuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getUrlDanhMuc() {
        return urlDanhMuc;
    }

    public void setUrlDanhMuc(String urlDanhMuc) {
        this.urlDanhMuc = urlDanhMuc;
    }
}
