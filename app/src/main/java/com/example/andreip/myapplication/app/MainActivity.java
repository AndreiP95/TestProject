package com.example.andreip.myapplication.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RetroFitAdapter retroFitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.ETTitleRecipe);
        swipeRefreshLayout = findViewById(R.id.SRLRefresh);

        recyclerView = findViewById(R.id.RVRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());

        retroFitAdapter = new RetroFitAdapter(this);

        retroFitAdapter.getRecipes("");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(editText.getText().toString());
            }

        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 2)
                    retroFitAdapter.getRecipes(s.toString());
                else
                    retroFitAdapter.getRecipes("");
            }

        @Override
        public void afterTextChanged (Editable s){

        }
    });
}

    public void refreshData(final String searchText) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (searchText.length() > 2)
                    retroFitAdapter.getRecipes(searchText);
                else
                    retroFitAdapter.getRecipes("");

                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}


