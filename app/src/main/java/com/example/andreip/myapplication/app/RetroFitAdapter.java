package com.example.andreip.myapplication.app;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitAdapter {

    private Retrofit retrofit;
    private ArrayList<Recipe> recipeArrayList = null;
    private MainActivity mainActivity;

    public RetroFitAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ArrayList<Recipe> getRecipeArrayList() {
        return recipeArrayList;
    }

    public void setRecipeArrayList(ArrayList<Recipe> recipeArrayList) {
        this.recipeArrayList = recipeArrayList;
    }

    public static OkHttpClient getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    private void initializeRetroFit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.recipepuppy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    public void getRecipes() {
        initializeRetroFit();
        List<String> veggies = new ArrayList<>();
        veggies.add("onions");
        veggies.add("garlic");
        RecipePuppyAPI service = retrofit.create(RecipePuppyAPI.class);

        Call<RecipeResponse> request = service.getRecipies(veggies, "omelet");
        request.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d("TITLE", response.body().getRecipes().get(0).getIngredients());
                        setRecipeArrayList(response.body().getRecipes());
                        ((MyAdapter) mainActivity.getRecyclerView().getAdapter())
                                .addAllItems(getRecipeArrayList());
                    }
                } else {
                    Log.d("FAIL", response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e("FAIL", t.getMessage() + " ", t);
            }
        });

    }
}


