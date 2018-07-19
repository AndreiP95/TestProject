package com.example.andreip.myapplication.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.ETTitleRecipe);

        recyclerView = findViewById(R.id.RVRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());


        final RetroFitAdapter retroFitAdapter = new RetroFitAdapter(this);

        retroFitAdapter.getRecipes("");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 2)
                    retroFitAdapter.getRecipes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}


