package com.surfcourse.nek.moviemusic.mainpage;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.SearchResultActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);
    VKSdk.login(this, "1");

    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
      performSearch(query);
    }

    RecyclerView recyclerViewNew = (RecyclerView) findViewById(R.id.resview_new);
    RecyclerView recyclerViewTop = (RecyclerView) findViewById(R.id.resview_top);

    MovieAdapter.OnClickListener listener = new MovieAdapter.OnClickListener() {
      @Override
      public void onClick(View v, Movie movie) {
        SearchResultActivity.start(MainPageActivity.this, movie);
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

  public static List<Movie> getMovieList() {
    int[] drawables = new int[] {R.drawable.mock0, R.drawable.mock1, R.drawable.mock2,
            R.drawable.mock3, R.drawable.mock4};
    String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.  type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ";
    description = description + description + description + description;
    ArrayList<Movie> movieList = new ArrayList<>(20);
    for (int i=0; i<20; i++){
      movieList.add(new Movie("movie " + i, drawables[i % 5], 1995 - i, description));
    }

    return movieList;
  }

  private void performSearch(String query) {
    List<Movie> movie = MainPageActivity.getMovieList();
    SearchResultActivity.start(this, new Movie(query, R.drawable.mock4, 2003, "descp"));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.options_menu, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );

    return true;
  }



  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
      @Override
      public void onResult(VKAccessToken res) {
// Пользователь успешно авторизовался
        Toast.makeText(getApplicationContext(),"успех", Toast.LENGTH_SHORT).show();
      }
      @Override
      public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
      }
    })) {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }
}

