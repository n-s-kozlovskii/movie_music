package com.surfcourse.nek.moviemusic.mainpage;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.MovieInfoActivity;
import com.surfcourse.nek.moviemusic.networking.models.RetrofitProvider;
import com.surfcourse.nek.moviemusic.networking.models.api.MovieDbApi;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.MovieDbResponse;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.Result;
import com.surfcourse.nek.moviemusic.search.SearchListActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainPageActivity extends AppCompatActivity implements
        View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

  private DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);

    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
      performSearch(query);
    }

    initBarAndDrawer();
    initRecyclers();

    // Nav
    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    findViewById(R.id.login_btn).setOnClickListener(this);
    findViewById(R.id.update_login_btn).setOnClickListener(this);
    findViewById(R.id.test_con).setOnClickListener(this);
    updateLoginState();
  }

  private void initBarAndDrawer() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.activity_main_page);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
    );
    drawer.setDrawerListener(toggle);
    toggle.syncState();
  }

  private void initRecyclers() {
    RecyclerView recyclerViewNew = (RecyclerView) findViewById(R.id.resview_new);
    RecyclerView recyclerViewTop = (RecyclerView) findViewById(R.id.resview_top);

    MovieAdapter.OnClickListener listener = (v, movie) -> MovieInfoActivity.start(MainPageActivity.this, movie);
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.options_menu, menu);

    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

    return true;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
      @Override
      public void onResult(VKAccessToken res) {
        // Пользователь успешно авторизовался
        Toast.makeText(getApplicationContext(), "успех", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onError(VKError error) {
        // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
      }
    })) {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.login_btn: {
        if (VKSdk.isLoggedIn()) {
          VKSdk.logout();
        } else {
          VKSdk.login(this, "1");
        }
        updateLoginState();
        break;
      }
      case R.id.update_login_btn: {
        updateLoginState();
        break;
      }
      case R.id.test_con: {
        testMovieDbConnection();
        break;
      }
    }
  }

  private void testMovieDbConnection() {
    RetrofitProvider pr = RetrofitProvider.getRetrofitProvider();
    Retrofit retrofit = pr.getMovieDbRetrofit();
    MovieDbApi api = retrofit.create(MovieDbApi.class);
    api.getMoviesByTitle(pr.getMovieDbKey(),"Transformers")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(movies -> printMovie(movies));
  }

  private void printMovie(MovieDbResponse movies) {
    Result res = movies.getResults().get(0);
    Movie movie = new Movie(res.getTitle(), res.getPosterPath(), res.getReleaseDate(), res.getOverview());
    MovieInfoActivity.start(this, movie);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_page);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.nav_share: {
        Toast.makeText(this, "shared", Toast.LENGTH_SHORT).show();
        break;
      }
    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public List<Movie> getMovieList() {
    int[] drawables = new int[]{
            R.drawable.mock0,
            R.drawable.mock1,
            R.drawable.mock2,
            R.drawable.mock3,
            R.drawable.mock4,
            R.drawable.mock5,
            R.drawable.mock6,
            R.drawable.mock7,
            R.drawable.mock8,
            R.drawable.mock9
    };
    String description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.  type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. ";
    description = description + description + description + description;
    ArrayList<Movie> movieList = new ArrayList<>(20);
    for (int i = 0; i < 20; i++) {
      movieList.add(new Movie("movie " + i, drawables[i % 10], Integer.toString(1995 - i), description));
    }

    return movieList;
  }

  private boolean isConnected(){
    ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null? activeNetwork.isConnected(): false;
  }

  private void performSearch(String query) {
    if (!isConnected()){
      Toast.makeText(this, "No network", Toast.LENGTH_LONG).show();
      return;
    }

    RetrofitProvider pr = RetrofitProvider.getRetrofitProvider();
    Retrofit retrofit = pr.getMovieDbRetrofit();
    MovieDbApi api = retrofit.create(MovieDbApi.class);
    api.getMoviesByTitle(pr.getMovieDbKey(),query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(movies -> showResults(movies.getResults()));

  }

  private void showResults(List<Result> results) {
    SearchListActivity.start(this, results);
  }

  private void updateLoginState() {
    TextView textView = (TextView) findViewById(R.id.login_tv);
    Button btn = (Button) findViewById(R.id.login_btn);
    if (VKSdk.isLoggedIn()) {

      textView.setText("You're logged in");
      btn.setText("Log out");
    } else {
      textView.setText("You're not logged in");
      btn.setText("Log in");
    }
  }
}

