package com.surfcourse.nek.moviemusic;

import android.app.Application;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.vk.sdk.*;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nek on 23.02.17.
 */

public class MyApp extends Application {
  private static String TAG = MyApp.class.getSimpleName();

  @Override
  public void onCreate() {
    super.onCreate();
    VKSdk.initialize(getApplicationContext());


  }
}
