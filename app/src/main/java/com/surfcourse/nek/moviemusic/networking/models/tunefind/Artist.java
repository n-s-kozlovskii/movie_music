package com.surfcourse.nek.moviemusic.networking.models.tunefind;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("tunefind_url")
  @Expose
  private String tunefindUrl;
  @SerializedName("tunefind_api_url")
  @Expose
  private String tunefindApiUrl;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTunefindUrl() {
    return tunefindUrl;
  }

  public void setTunefindUrl(String tunefindUrl) {
    this.tunefindUrl = tunefindUrl;
  }

  public String getTunefindApiUrl() {
    return tunefindApiUrl;
  }

  public void setTunefindApiUrl(String tunefindApiUrl) {
    this.tunefindApiUrl = tunefindApiUrl;
  }

}