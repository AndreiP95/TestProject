package com.example.andreip.myapplication.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import SQLData.RecipeDb;
import SQLData.WordRepository;


public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RetroFitAdapter retroFitAdapter;
    private WordRepository wordRepository;
    private LiveData<List<RecipeDb>> mLiveData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.ETTitleRecipe);
        swipeRefreshLayout = findViewById(R.id.SRLRefresh);

        recyclerView = findViewById(R.id.RVRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftKeyboard();
                return false;
            }
        });

        retroFitAdapter = new RetroFitAdapter(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(editText.getText().toString());
            }

        });

        wordRepository = new WordRepository(getApplication());

        mLiveData = wordRepository.getAllWords();
        mLiveData.observeForever(new Observer<List<RecipeDb>>() {
            @Override
            public void onChanged(@Nullable List<RecipeDb> recipeDbs) {
                if (mLiveData.getValue().equals(null)) {
                    retroFitAdapter.getRecipes("", wordRepository);
                } else {
                    ((MyAdapter) recyclerView.getAdapter()).setDbArrayList(mLiveData.getValue());
                    Log.d("DEBUG_DB", mLiveData.getValue().size() + " ");
                }
            }
        });

        retroFitAdapter.getRecipes("", wordRepository);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2)
                    retroFitAdapter.getRecipes(s.toString(), wordRepository);
                else
                    retroFitAdapter.getRecipes("", wordRepository);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void refreshData(final String searchText) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (searchText.length() > 2)
                    retroFitAdapter.getRecipes(searchText, wordRepository);
                else
                    retroFitAdapter.getRecipes("", wordRepository);

                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}


