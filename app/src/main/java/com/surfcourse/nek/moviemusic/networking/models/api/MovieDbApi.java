package com.surfcourse.nek.moviemusic.networking.models.api;

import com.surfcourse.nek.moviemusic.networking.models.themoviedb.MovieDbResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nek on 25.03.17.
 */

public interface MovieDbApi {
  @GET("movie")
  Observable<MovieDbResponse> getMoviesByTitle(
          @Query("api_key") String key,
          @Query("query") String query);
}
