package com.surfcourse.nek.moviemusic.mainpage;

public class Movie {
  private String title;
  private int drawableId;
  private String year;
  private String description;
  private String url;

  public Movie(String title, int drawableId, String year, String description) {
    this.title = title;
    this.drawableId = drawableId;
    this.year = year;
    this.description = description;
    url = null;
  }

  public Movie(String title,String url, String year, String description) {
    this.title = title;
    this.year = year;
    this.description = description;
    this.url = url;
    drawableId = 0;
  }

  public String getTitle() {
    return title;
  }

  public int getDrawableId() {
    return drawableId;
  }

  public String getImageUrl(){return  url;}

  public String getYear() {
    return year;
  }

  public String getDescription() {
    return description;
  }
}
