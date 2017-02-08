package com.surfcourse.nek.moviemusic.songs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.songs.SongListFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
@SuppressWarnings("WeakerAccess")
public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {


  private final List<Song> songs;
  private final OnListFragmentInteractionListener listener;

  public SongListAdapter(List<Song> items, OnListFragmentInteractionListener listener) {
    songs = items;
    this.listener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.fragment_song, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.song = songs.get(position);
    holder.title.setText(songs.get(position).getTitle());
    holder.author.setText(songs.get(position).getAuthor());

    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != listener) {
          // Notify the active callbacks interface (the activity, if the
          // fragment is attached to one) that an item has been selected.
          listener.onListFragmentInteraction(holder.song);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return songs.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public final View view;
    public final TextView title;
    public final TextView author;
    public final TextView movie;
    public Song song;

    public ViewHolder(View view) {
      super(view);
      this.view = view;
      title = (TextView) view.findViewById(R.id.title);
      author = (TextView) view.findViewById(R.id.author);
      movie = (TextView) view.findViewById(R.id.movie);
    }

//    @Override
//    public String toString() {
//      return super.toString() + " '" + author.getText() + "'";
//    }
  }
}
