package com.surfcourse.nek.moviemusic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surfcourse.nek.moviemusic.songs.Song;

import java.util.List;

/**
 * Created by nek on 09.12.16.
 */

public class SongsAdapter extends android.support.v7.widget.RecyclerView.Adapter<SongsAdapter.SongViewHolder> {
  private List<Song> songsList;

  public SongsAdapter(List<Song> songsList) {
    this.songsList = songsList;
  }

  @Override
  public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song, parent, false);
    return new SongViewHolder(view);
  }

  @Override
  public void onBindViewHolder(SongViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class SongViewHolder extends RecyclerView.ViewHolder {
    public SongViewHolder(View itemView) {
      super(itemView);
    }
  }
}
