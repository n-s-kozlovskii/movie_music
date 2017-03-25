package com.surfcourse.nek.moviemusic.networking.models.tunefind;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("countries")
  @Expose
  private List<String> countries = null;
  @SerializedName("url")
  @Expose
  private String url;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<String> getCountries() {
    return countries;
  }

  public void setCountries(List<String> countries) {
    this.countries = countries;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
