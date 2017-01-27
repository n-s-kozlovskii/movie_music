package com.surfcourse.nek.moviemusic.mainpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.surfcourse.nek.moviemusic.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{
  private List<Movie> movies;

  public MovieAdapter(List<Movie> movies) {
    this.movies = movies;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public ImageView poster;

    public MyViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.item_text);
      poster = (ImageView) itemView.findViewById(R.id.item_img);
    }
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    Movie movie = movies.get(position);
    holder.title.setText(movie.getTitle());
    holder.poster.setImageResource(movie.getDrawableId());
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }
}