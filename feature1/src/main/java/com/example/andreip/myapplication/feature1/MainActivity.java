package com.example.andreip.myapplication.feature1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String[] myList = {"asta", "este", "viata"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RVRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(myList));


    }

}


