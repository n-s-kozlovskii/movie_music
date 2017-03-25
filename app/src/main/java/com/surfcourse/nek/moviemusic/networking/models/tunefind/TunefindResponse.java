package com.surfcourse.nek.moviemusic.networking.models.tunefind;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TunefindResponse {

  @SerializedName("songs")
  @Expose
  private List<Song> songs = null;
  @SerializedName("event")
  @Expose
  private Event event;

  public List<Song> getSongs() {
    return songs;
  }

  public void setSongs(List<Song> songs) {
    this.songs = songs;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

}
