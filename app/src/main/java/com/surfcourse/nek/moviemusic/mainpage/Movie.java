package com.surfcourse.nek.moviemusic.mainpage;

/**
 * Created by nek on 26.01.17.
 */
public class Movie {
  private String title;
  private int drawableId;

  public String getTitle() {
    return title;
  }

  public int getDrawableId() {
    return drawableId;
  }

  public Movie(String title, int drawable) {

    this.title = title;
    this.drawableId = drawable;
  }
}
