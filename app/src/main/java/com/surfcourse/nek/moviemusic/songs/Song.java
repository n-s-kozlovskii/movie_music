package com.surfcourse.nek.moviemusic.songs;

public class Song {

  private int id;
  private String title;
  private String author;
  private String movie;
  private String image;

  public Song(int id, String title, String author, String movie, String image) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.movie = movie;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getMovie() {
    return movie;
  }

  public String getImage() {
    return image;
  }
}
