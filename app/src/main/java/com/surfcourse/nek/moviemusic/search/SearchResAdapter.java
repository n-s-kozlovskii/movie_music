package com.surfcourse.nek.moviemusic.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surfcourse.nek.moviemusic.R;
import com.surfcourse.nek.moviemusic.networking.models.themoviedb.Result;

import java.util.List;

/**
 * Created by nek on 26.03.17.
 */

public class SearchResAdapter extends RecyclerView.Adapter<SearchResAdapter.MyViewHolder> {
  public interface OnClickListener{
    void onClick(View v, Result result);
  }

  private List<Result> results;
  private OnClickListener listener;

  public SearchResAdapter(List<Result> results, OnClickListener listener) {
    this.results = results;
    this.listener = listener;
  }

  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchres_rv_item, parent,false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MyViewHolder holder, int position) {
    final Result res = results.get(position);
    holder.text.setText(res.getTitle());
    if (listener != null){
      holder.itemView.setOnClickListener(v -> listener.onClick(v, res));
    }
  }

  @Override
  public int getItemCount() {
    return results.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView text;

    public MyViewHolder(View itemView) {
      super(itemView);
      text = (TextView) itemView.findViewById(R.id.search_item_text);
    }
  }
}
