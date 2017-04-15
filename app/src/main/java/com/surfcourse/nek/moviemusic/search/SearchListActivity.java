package com.surfcourse.nek.moviemusic.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.surfcourse.nek.moviemusic.MovieInfoActivity;
import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.mainpage.Movie;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchListActivity extends AppCompatActivity {
  private static final String KEY_RESULTS = "results";
  private List<Result> responce;

  public static void start(Context context, List<Result> results){
    Log.d("search", "in start: "+ Integer.toString(results.size()));
    Intent intent = new Intent(context, SearchListActivity.class);
    intent.putParcelableArrayListExtra(KEY_RESULTS, new ArrayList<>(results));
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search_list);
    Intent intent = getIntent();
    responce = intent.getParcelableArrayListExtra(KEY_RESULTS);
    Log.d("search", "in onCreate: "+ Integer.toString(responce.size()));
    initRecycler();
  }


  private void initRecycler(){
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview_search_result);
    SearchResAdapter adapter = new SearchResAdapter(responce, (v, result) -> showMovie(result));
    RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    recyclerView.setLayoutManager(manager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  private void showMovie(Result result) {
    MovieInfoActivity.start(this, new Movie(result.getTitle(), result.getPosterPath(),
            result.getReleaseDate(), result.getOverview()));
  }
}
