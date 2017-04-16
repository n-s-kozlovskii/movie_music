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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.SearchResultActivity;
import com.surfcourse.nek.moviemusic.MovieInfoActivity;
import com.surfcourse.nek.moviemusic.networking.models.RetrofitProvider;
import com.surfcourse.nek.moviemusic.networking.models.api.MovieDbApi;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.MovieDbResponse;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.Result;
import com.surfcourse.nek.moviemusic.search.SearchListActivity;
import com.surfcourse.nek.moviemusic.util.CircularTransformation;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainPageActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

  private DrawerLayout drawer;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_page);

    Intent intent = getIntent();
    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);
      performSearch(query);
    }

    initBarAndDrawer();
    initRecyclers();

    updateUserInfo();
  }

  private void initBarAndDrawer() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.activity_main_page);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
    );
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  private void initRecyclers() {
    RecyclerView recyclerViewNew = (RecyclerView) findViewById(R.id.res_view_new);
    RecyclerView recyclerViewTop = (RecyclerView) findViewById(R.id.res_view_top);

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
    boolean success = VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
      @Override
      public void onResult(VKAccessToken res) {
        Toast.makeText(getApplicationContext(),
                "Вы успешно авторизовались", Toast.LENGTH_SHORT).show();
        updateUserInfo();
      }

      @Override
      public void onError(VKError error) {
        Toast.makeText(MainPageActivity.this, error.errorMessage, Toast.LENGTH_SHORT).show();
        Log.d("VK Login Error", error.errorMessage + " " + error.errorReason + " " + error.errorCode);
      }
    });

    if (!success) {
      super.onActivityResult(requestCode, resultCode, data);
    }
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
      case R.id.nav_vk: {
        if (VKSdk.isLoggedIn()) {
          VKSdk.logout();
        } else {
          VKSdk.login(this, VKScope.NOTIFY);
        }
        updateUserInfo();
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

  private void updateUserInfo() {
    String newTitle;
    if (VKSdk.isLoggedIn()) {
      newTitle = "Выйти";
      VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_200"));
      request.executeWithListener(new VKRequest.VKRequestListener() {
        @Override
        public void onComplete(VKResponse response) {
          try {
            JSONObject user = response.json.getJSONArray("response").getJSONObject(0);
            String name = user.getString("first_name")  + " " + user.getString("last_name");
            String photo = user.getString("photo_200");

            ((TextView) navigationView.getHeaderView(0).findViewById(R.id.textView))
                    .setText(name);
            Picasso.with(MainPageActivity.this)
                    .load(photo)
                    .resize(150, 150)
                    .transform(new CircularTransformation())
                    .into((ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView));

            Log.d("VK request", "completed " + name + " " + photo);
          } catch (JSONException e) {
            e.printStackTrace();
          }

        }

        @Override
        public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
          Log.d("VK request", "attemptFailed");
        }

        @Override
        public void onError(VKError error) {
          Log.d("VK request", "error " + error.errorMessage);
        }

        @Override
        public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
          Log.d("VK request", "progress...");
        }
      });
    } else {
      newTitle = "Авторизаваться через vk";
      Picasso.with(MainPageActivity.this)
              .load(R.drawable.mock9)
              .resize(100, 100)
              .into((ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView));
      ((TextView) navigationView.getHeaderView(0).findViewById(R.id.textView))
              .setText(R.string.name_placeholder);
    }
    MenuItem item = navigationView.getMenu().findItem(R.id.nav_vk);
    item.setTitle(newTitle);

    // Get name and stuff
  }
}

