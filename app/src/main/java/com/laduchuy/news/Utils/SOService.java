package com.laduchuy.news.Utils;

import com.laduchuy.news.WeatherObject.SOAnswersResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {


    @GET("weather?q=Hanoi&appid=559e9a92d09586b2faced211d05bb1dd")
    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
