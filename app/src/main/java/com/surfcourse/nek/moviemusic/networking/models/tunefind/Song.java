package com.surfcourse.nek.moviemusic.networking.models.tunefind;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("confidence")
  @Expose
  private String confidence;
  @SerializedName("scene")
  @Expose
  private String scene;
  @SerializedName("date_updated")
  @Expose
  private String dateUpdated;
  @SerializedName("tunefind_url")
  @Expose
  private String tunefindUrl;
  @SerializedName("tunefind_api_url")
  @Expose
  private String tunefindApiUrl;
  @SerializedName("artist")
  @Expose
  private Artist artist;
  @SerializedName("stores")
  @Expose
  private List<Store> stores = null;
  @SerializedName("preview_url")
  @Expose
  private String previewUrl;

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

  public String getConfidence() {
    return confidence;
  }

  public void setConfidence(String confidence) {
    this.confidence = confidence;
  }

  public String getScene() {
    return scene;
  }

  public void setScene(String scene) {
    this.scene = scene;
  }

  public String getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(String dateUpdated) {
    this.dateUpdated = dateUpdated;
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

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public List<Store> getStores() {
    return stores;
  }

  public void setStores(List<Store> stores) {
    this.stores = stores;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

}