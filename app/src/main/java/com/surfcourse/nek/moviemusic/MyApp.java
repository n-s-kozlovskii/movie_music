package com.surfcourse.nek.moviemusic;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by nek on 23.02.17.
 */

public class MyApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    VKSdk.initialize(getApplicationContext());
  }
}
