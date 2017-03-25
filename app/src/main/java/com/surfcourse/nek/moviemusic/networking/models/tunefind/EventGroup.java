package com.surfcourse.nek.moviemusic.networking.models.tunefind;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventGroup {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("name_stub")
  @Expose
  private String nameStub;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("number")
  @Expose
  private String number;
  @SerializedName("date_updated")
  @Expose
  private String dateUpdated;
  @SerializedName("tunefind_url")
  @Expose
  private String tunefindUrl;
  @SerializedName("tunefind_api_url")
  @Expose
  private String tunefindApiUrl;
  @SerializedName("thumb_url")
  @Expose
  private String thumbUrl;
  @SerializedName("air_date_start")
  @Expose
  private String airDateStart;
  @SerializedName("air_date_end")
  @Expose
  private String airDateEnd;
  @SerializedName("episode_count")
  @Expose
  private Integer episodeCount;
  @SerializedName("song")
  @Expose
  private Object song;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNameStub() {
    return nameStub;
  }

  public void setNameStub(String nameStub) {
    this.nameStub = nameStub;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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

  public String getThumbUrl() {
    return thumbUrl;
  }

  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
  }

  public String getAirDateStart() {
    return airDateStart;
  }

  public void setAirDateStart(String airDateStart) {
    this.airDateStart = airDateStart;
  }

  public String getAirDateEnd() {
    return airDateEnd;
  }

  public void setAirDateEnd(String airDateEnd) {
    this.airDateEnd = airDateEnd;
  }

  public Integer getEpisodeCount() {
    return episodeCount;
  }

  public void setEpisodeCount(Integer episodeCount) {
    this.episodeCount = episodeCount;
  }

  public Object getSong() {
    return song;
  }

  public void setSong(Object song) {
    this.song = song;
  }

}
