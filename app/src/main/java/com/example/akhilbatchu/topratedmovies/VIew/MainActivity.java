package com.example.akhilbatchu.topratedmovies.VIew;

import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.akhilbatchu.topratedmovies.Model.Movies;
import com.example.akhilbatchu.topratedmovies.Model.Result;
import com.example.akhilbatchu.topratedmovies.R;
import com.example.akhilbatchu.topratedmovies.Services.Endpoints;
import com.example.akhilbatchu.topratedmovies.Services.RetrofitInstance;
import com.example.akhilbatchu.topratedmovies.adapter.MovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ArrayList<Result> mov;
    SwipeRefreshLayout swipe;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mov = new ArrayList<>();
        getPopularmovies();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularmovies();
            }
        });

    }
    public void getPopularmovies()
    {
        Endpoints endpoints = RetrofitInstance.getCombine().create(Endpoints.class);
        Call<Movies> call = endpoints.getPopularMovies("b69f521299ab171bcbce76d4d2d80e05");
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Movies    movies =  response.body();
                if(movies!=null && movies.getResults()!=null)
                {
                    mov  = (ArrayList)movies.getResults();
                }
                showInRecylerView();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.i("baby",t.fillInStackTrace()+"S");
            }
        });
    }

   void showInRecylerView()
   {
       RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
       MovieAdapter adapter = new MovieAdapter(mov,getApplicationContext());
       if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
           recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
       }
       else{
           recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));

       }
       recyclerView.setItemAnimator(new DefaultItemAnimator());
       recyclerView.setAdapter(adapter);
       adapter.notifyDataSetChanged();
   }
}
