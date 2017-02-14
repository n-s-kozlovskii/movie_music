package com.surfcourse.nek.moviemusic.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.surfcourse.nek.moviemusic.R;


public class SearchActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    Log.d("Search", "on Create");


    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    }
  }



}
