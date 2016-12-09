package com.surfcourse.nek.moviemusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.recent_btn).setOnClickListener(this);
    findViewById(R.id.new_btn).setOnClickListener(this);
    findViewById(R.id.best_btn).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.recent_btn: {
        break;
      }
      case R.id.new_btn: {
        break;
      }
      case R.id.best_btn: {
        break;
      }
    }
  }
}
