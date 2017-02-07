package com.surfcourse.nek.moviemusic.mainpage;

public class Movie {
  private String title;
  private int drawableId;
  private int year;
  private String description;

  public Movie(String title, int drawableId, int year, String description) {
    this.title = title;
    this.drawableId = drawableId;
    this.year = year;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public int getDrawableId() {
    return drawableId;
  }

  public int getYear() {
    return year;
  }

  public String getDescription() {
    return description;
  }
}
