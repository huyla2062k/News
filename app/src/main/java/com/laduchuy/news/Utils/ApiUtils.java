package com.laduchuy.news.Utils;

public class ApiUtils {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}