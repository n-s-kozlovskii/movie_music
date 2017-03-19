package com.surfcourse.nek.moviemusic;

import android.app.Application;
import android.util.Log;

import com.vk.sdk.*;

/**
 * Created by nek on 23.02.17.
 */

public class MyApp extends Application {
  public static String API_USER = BuildConfig.API_USER;
  public static String API_KEY = BuildConfig.API_KEY;
  private static String TAG = MyApp.class.getSimpleName();

  @Override
  public void onCreate() {
    Log.d(TAG, API_KEY);
    super.onCreate();
    VKSdk.initialize(getApplicationContext());
  }
}
