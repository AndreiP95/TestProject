package com.example.andreip.myapplication.app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipePuppyAPI {

    @get:GET("api/?i=onions,garlic&q=omelet&p=3")
    val recipiesHardcoded: Call<String>

    @GET("api")
    fun getRecipies(@Query("i") veggies: List<String>,
                    @Query("q") recipe: String): Call<RecipeResponse>

}
