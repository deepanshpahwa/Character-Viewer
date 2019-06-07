package com.example.characterviewer.API;


import com.example.characterviewer.Bean.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface used by Retrofit to gt data from DuckDuckGO
 */
public interface DuckDuckGoAPI {
    @GET("/")
    Call<Example> getCharacters(@Query("q")String query, @Query("format")String format);
}
