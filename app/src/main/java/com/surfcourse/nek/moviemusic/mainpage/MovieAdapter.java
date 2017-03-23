package com.surfcourse.nek.moviemusic.mainpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.surfcourse.nek.moviemusic.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

  public interface OnClickListener {
    void onClick(View v, Movie movie);
  }

  private List<Movie> movies;
  private OnClickListener listener;

  public MovieAdapter(List<Movie> movies, OnClickListener listener) {
    this.movies = movies;
    this.listener = listener;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    final Movie movie = movies.get(position);
    holder.title.setText(movie.getTitle());
    //holder.poster.setImageResource(movie.getDrawableId());
    Picasso.with(holder.poster.getContext()).load(movie.getDrawableId())
            .resize(100, 140)
            .into(holder.poster);

    if (listener != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onClick(v, movie);
        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return movies.size();
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
}
