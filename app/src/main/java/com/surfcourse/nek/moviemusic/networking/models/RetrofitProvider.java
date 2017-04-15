package com.surfcourse.nek.moviemusic.networking.models;

import com.google.gson.GsonBuilder;
import com.surfcourse.nek.moviemusic.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nek on 25.03.17.
 */

public class RetrofitProvider {
  private static RetrofitProvider retrofitProvider;
  private Retrofit movieDbRetrofit;
  private String TUNEF_API_USER;
  private String TUNEF_API_KEY;
  private String MOVIEDB_KEY;

  private RetrofitProvider() {
    movieDbRetrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/search/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build();
    TUNEF_API_USER = BuildConfig.API_USER;
    TUNEF_API_KEY = BuildConfig.API_KEY;
    MOVIEDB_KEY = BuildConfig.MOVIEDB_KEY;
  }

  public Retrofit getMovieDbRetrofit() {
    return movieDbRetrofit;
  }

  public String getTunefindUser() {
    return TUNEF_API_USER;
  }

  public String getTunefindKey() {
    return TUNEF_API_KEY;
  }

  public static RetrofitProvider getRetrofitProvider() {
    if (retrofitProvider == null) {
      retrofitProvider = new RetrofitProvider();
    }
    return retrofitProvider;
  }

  public String getMovieDbKey() {
    return MOVIEDB_KEY;
  }
}
