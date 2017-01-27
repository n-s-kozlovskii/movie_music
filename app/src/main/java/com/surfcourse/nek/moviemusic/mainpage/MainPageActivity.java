package com.surfcourse.nek.moviemusic.mainpage;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.surfcourse.nek.moviemusic.R;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);

    RecyclerView recyclerViewNew = (RecyclerView) findViewById(R.id.resview_new);
    RecyclerView recyclerViewTop = (RecyclerView) findViewById(R.id.resview_top);

    MovieAdapter movieAdapterNew = new MovieAdapter(getMovieList());
    MovieAdapter movieAdapterTop = new MovieAdapter(getMovieList());

    RecyclerView.LayoutManager layoutManagerNew = new LinearLayoutManager(getApplicationContext(),
            LinearLayoutManager.HORIZONTAL, false);
    RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(getApplicationContext(),
            LinearLayoutManager.HORIZONTAL, false);


    recyclerViewNew.setLayoutManager(layoutManagerNew);
    recyclerViewNew.setItemAnimator(new DefaultItemAnimator());
    recyclerViewNew.setAdapter(movieAdapterNew);

    recyclerViewTop.setLayoutManager(layoutManagerTop);
    recyclerViewTop.setItemAnimator(new DefaultItemAnimator());
    recyclerViewTop.setAdapter(movieAdapterTop);




  }

  public List<Movie> getMovieList() {
    ArrayList<Movie> movieList = new ArrayList<>(20);
    for (int i=0; i<20; i++){
      movieList.add(new Movie("movie "+i, R.drawable.poster));
    }

    return movieList;
  }
}

