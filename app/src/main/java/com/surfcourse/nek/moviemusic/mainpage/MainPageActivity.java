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
import android.widget.Toast;

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

    MovieAdapter.OnClickListener listener = new MovieAdapter.OnClickListener() {
      @Override
      public void onClick(View v, Movie movie) {
        Toast.makeText(MainPageActivity.this, "It's working!", Toast.LENGTH_SHORT).show();
      }
    };
    MovieAdapter movieAdapterNew = new MovieAdapter(getMovieList(), listener);
    MovieAdapter movieAdapterTop = new MovieAdapter(getMovieList(), listener);

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

