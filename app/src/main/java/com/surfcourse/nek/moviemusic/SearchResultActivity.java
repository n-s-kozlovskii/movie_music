package com.surfcourse.nek.moviemusic;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.surfcourse.nek.moviemusic.mainpage.Movie;
import com.surfcourse.nek.moviemusic.songs.Song;
import com.surfcourse.nek.moviemusic.songs.SongListFragment;

public class SearchResultActivity extends AppCompatActivity
        implements SongListFragment.OnListFragmentInteractionListener,
                   AppBarLayout.OnOffsetChangedListener {

  private static String KEY_TITLE = "title";
  private static String KEY_DRAWABLE = "drawable";
  private static String KEY_YEAR = "year";
  private static String KEY_DESCRIPTION = "description";

  private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
  private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
  private static final int ALPHA_ANIMATIONS_DURATION = 200;

  private boolean mIsTheTitleVisible = false;
  private boolean mIsTheTitleContainerVisible = true;

  private LinearLayout mTitleContainer;
  private TextView mTitle;
  private AppBarLayout mAppBarLayout;
  private Toolbar mToolbar;
  private ActionBar mActionBar;

  public static void start(Context context, Movie movie) {
    Intent intent = new Intent(context, SearchResultActivity.class);
    intent.putExtra(KEY_TITLE, movie.getTitle());
    intent.putExtra(KEY_DRAWABLE, movie.getDrawableId());
    intent.putExtra(KEY_YEAR, movie.getYear());
    intent.putExtra(KEY_DESCRIPTION, movie.getDescription());
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_result);

    Intent intent = getIntent();

    bindActivity();

    mAppBarLayout.addOnOffsetChangedListener(this);

    startAlphaAnimation(mTitle, 0, View.INVISIBLE);

    Picasso.with(this)
            .load(intent.getIntExtra(KEY_DRAWABLE, 0))
            .resize(400, 600)
            .into((ImageView) findViewById(R.id.poster_img_view));
    ((TextView) findViewById(R.id.title_text)).setText(intent.getStringExtra(KEY_TITLE));
    ((TextView) findViewById(R.id.title_text_collapsed)).setText(intent.getStringExtra(KEY_TITLE));
    ((TextView) findViewById(R.id.year_text))
            .setText(String.valueOf(intent.getIntExtra(KEY_YEAR, 1990)));
    ((TextView) findViewById(R.id.description_text)).setText(intent.getStringExtra(KEY_DESCRIPTION));
  }

  @Override
  public void onListFragmentInteraction(Song item) {

  }

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
    int maxScroll = appBarLayout.getTotalScrollRange();
    float percentage = (float) Math.abs(offset) / (float) maxScroll;

    handleAlphaOnTitle(percentage);
    handleToolbarTitleVisibility(percentage);
  }

  private void bindActivity() {
    mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
    setSupportActionBar(mToolbar);
    mActionBar = getSupportActionBar();
    mTitle = (TextView) findViewById(R.id.title_text_collapsed);
    mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
    mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);

    mActionBar.setDisplayShowTitleEnabled(false);
  }

  private void handleToolbarTitleVisibility(float percentage) {
    if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

      if(!mIsTheTitleVisible) {
        startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
        mIsTheTitleVisible = true;
      }

    } else {

      if (mIsTheTitleVisible) {
        startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
        mIsTheTitleVisible = false;
      }
    }
  }

  private void handleAlphaOnTitle(float percentage) {
    if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
      if(mIsTheTitleContainerVisible) {
        startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
        mIsTheTitleContainerVisible = false;
        mActionBar.setDisplayHomeAsUpEnabled(true);
      }

    } else {

      if (!mIsTheTitleContainerVisible) {
        startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
        mIsTheTitleContainerVisible = true;
        mActionBar.setDisplayHomeAsUpEnabled(false);
      }
    }
  }

  public static void startAlphaAnimation (View v, long duration, int visibility) {
    AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
            ? new AlphaAnimation(0f, 1f)
            : new AlphaAnimation(1f, 0f);

    alphaAnimation.setDuration(duration);
    alphaAnimation.setFillAfter(true);
    v.startAnimation(alphaAnimation);
  }
}
