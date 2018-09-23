package com.example.akhilbatchu.topratedmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.akhilbatchu.topratedmovies.Model.Result;
import com.example.akhilbatchu.topratedmovies.R;

import java.util.ArrayList;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private ArrayList<Result> movies;
    private Context mctx;

    public MovieAdapter(ArrayList<Result> movies, Context mctx) {
        this.movies = movies;
        this.mctx = mctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(movies.get(position).getOriginalTitle());
        holder.rate.setText(String.valueOf(movies.get(position).getVoteAverage()));
        String imagepath = "https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath();

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading);
        Glide.with(mctx).
                load(imagepath)
                .apply(requestOptions).into(holder.img );
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title,rate;

        public ViewHolder(View itemView) {
            super(itemView);
            img= (ImageView)itemView.findViewById(R.id.ivMovie);
            title = (TextView)itemView.findViewById(R.id.tvTitle);
            rate = (TextView)itemView.findViewById(R.id.tvRating);
        }
    }
}
