package com.example.andreip.myapplication.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipePuppyAPI {

    @GET("api")
    Call<RecipeResponse> getRecipies(@Query("i") List<String> veggies,
                                     @Query("q") String recipe);

    @GET("api/?i=onions,garlic&q=omelet&p=3")
    Call<String> getRecipiesHardcoded();

}
