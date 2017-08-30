package com.example.android.lagosjavadevs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.lagosjavadevs.Network.EndpointInterface;
import com.example.android.lagosjavadevs.dataclasses.Item;
import com.example.android.lagosjavadevs.dataclasses.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.v7.widget.RecyclerView.LayoutManager;

public class MainActivity extends AppCompatActivity {
    private final static String BASE_URL = "https://api.github.com/search/";
    private final static  String USERS_TAG = "all_users";
    List<Item> users;
    RecyclerView recyclerView;
    JavaDevAdapter devAdapter;
    LayoutManager layoutManager;
    Context mContext;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = getBaseContext();
        users = new ArrayList<>();
        if(savedInstanceState != null){
            users = savedInstanceState.getParcelableArrayList(USERS_TAG);
        }
        recyclerView =(RecyclerView) findViewById(R.id.user_recycler_view);
        layoutManager = new LinearLayoutManager(mContext);
        devAdapter = new JavaDevAdapter(mContext,users);
        progressBar = (ProgressBar) findViewById(R.id.loading_bar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(devAdapter);
        recyclerView.setLayoutManager(layoutManager);
        getAllJavaDevs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            getAllJavaDevs();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllJavaDevs(){
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EndpointInterface apiService = retrofit.create(EndpointInterface.class);
        progressBar.setVisibility(View.VISIBLE);
        Call<Result> call = apiService.getJavaUsers();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                users.clear();
                users.addAll(response.body().getItems());
                devAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS_TAG,(ArrayList<Item>)users);
    }
}
