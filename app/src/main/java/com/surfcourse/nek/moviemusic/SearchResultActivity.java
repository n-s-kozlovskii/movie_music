package com.surfcourse.nek.moviemusic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.surfcourse.nek.moviemusic.mainpage.Movie;

public class SearchResultActivity extends AppCompatActivity {

  private static String KEY_TITLE = "title";
  private static String KEY_DRAWABLE = "drawable";
  private static String KEY_YEAR = "year";
  private static String KEY_DESCRIPTION = "description";

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

    ((ImageView) findViewById(R.id.poster_img_view))
            .setImageDrawable(getDrawable(intent.getIntExtra(KEY_DRAWABLE, 0)));
    ((TextView) findViewById(R.id.title_text)).setText(intent.getStringExtra(KEY_TITLE));
    ((TextView) findViewById(R.id.year_text))
            .setText(String.valueOf(intent.getIntExtra(KEY_YEAR, 1990)));
    ((TextView) findViewById(R.id.description_text)).setText(intent.getStringExtra(KEY_DESCRIPTION));
  }
}
