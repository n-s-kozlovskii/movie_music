package com.surfcourse.nek.moviemusic.networking.models.themoviedb;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * фильм с themovieDB
 */
public class Result implements Parcelable{

  @SerializedName("poster_path")
  @Expose
  private String posterPath;
  @SerializedName("adult")
  @Expose
  private Boolean adult;
  @SerializedName("overview")
  @Expose
  private String overview;
  @SerializedName("release_date")
  @Expose
  private String releaseDate;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("original_title")
  @Expose
  private String originalTitle;
  @SerializedName("original_language")
  @Expose
  private String originalLanguage;
  @SerializedName("title")
  @Expose
  private String title;


  protected Result(Parcel in) {
    posterPath = in.readString();
    overview = in.readString();
    releaseDate = in.readString();
    originalTitle = in.readString();
    originalLanguage = in.readString();
    title = in.readString();
  }

  public static final Creator<Result> CREATOR = new Creator<Result>() {
    @Override
    public Result createFromParcel(Parcel in) {
      return new Result(in);
    }

    @Override
    public Result[] newArray(int size) {
      return new Result[size];
    }
  };

  public String getPosterPath() {
    return posterPath;
  }




  public String getOverview() {
    return overview;
  }


  public String getReleaseDate() {
    return releaseDate;
  }



  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }



  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }



  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(posterPath);
    dest.writeString(overview);
    dest.writeString(releaseDate);
    dest.writeString(originalTitle);
    dest.writeString(originalLanguage);
    dest.writeString(title);
  }
}
