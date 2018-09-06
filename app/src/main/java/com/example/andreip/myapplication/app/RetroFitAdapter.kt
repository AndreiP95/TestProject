package com.example.andreip.myapplication.app

import android.util.Log

import java.util.ArrayList

import SQLData.RecipeDb
import SQLData.WordRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitAdapter(private val mainActivity: MainActivity) {

    private var retrofit: Retrofit? = null
    var recipeArrayList: List<Recipe> = ArrayList<Recipe> ()

    fun toRecipe(recipeDbList: List<RecipeDb>): ArrayList<Recipe>{
        var recipeArrayList = ArrayList<Recipe>()
        for (recipeDb in recipeDbList)
            recipeArrayList.add(Recipe(recipeDb))

        return recipeArrayList
    }

    fun filterRecipeArrayList(adapter: MyAdapter, recipeArrayList: List<Recipe>?, text: String) {

        val filterRecipeList = ArrayList<Recipe>()

        for (recipe in recipeArrayList!!)
            if (recipe.getTitle()!!.contains(text)) {
                filterRecipeList.add(recipe)
            }

        this.recipeArrayList = filterRecipeList
        adapter.refreshAllItems(filterRecipeList)
    }

    private fun initializeRetroFit() {
        retrofit = Retrofit.Builder()
                .baseUrl("http://www.recipepuppy.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }


    fun getRecipes(s: String, wordRepository: WordRepository) {
        initializeRetroFit()
        val veggies = ArrayList<String>()
        veggies.add("onions")
        veggies.add("garlic")
        val service = retrofit!!.create(RecipePuppyAPI::class.java)

        val request = service.getRecipies(veggies, "omelet")
        request.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.d("TITLE", response.body()!!.recipes!![0].getIngredients())
                        recipeArrayList = response.body()!!.recipes

                        if (!s.equals("", ignoreCase = true))
                            filterRecipeArrayList(mainActivity.recyclerView.adapter as MyAdapter, recipeArrayList, s)
                        else
                            (mainActivity.recyclerView.adapter as MyAdapter).addAllItems(recipeArrayList)

                        Log.d("TITLE_DB", wordRepository.getValueAllWords().toString() + " ")

                        wordRepository.insertAllRecipes(recipeArrayList)
                    }
                } else {
                    recipeArrayList = toRecipe(wordRepository.getValueAllWords())


                    if (!s.equals("", ignoreCase = true))
                        filterRecipeArrayList(mainActivity.recyclerView.adapter as MyAdapter, recipeArrayList, s)
                    else
                        (mainActivity.recyclerView.adapter as MyAdapter).addAllItems(recipeArrayList)
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {

                recipeArrayList = toRecipe(wordRepository.getValueAllWords())
                Log.e("FAIL", t.message + " ", t)

                if (!s.equals("", ignoreCase = true))
                    filterRecipeArrayList(mainActivity.recyclerView.adapter as MyAdapter, recipeArrayList, s)
                else
                    (mainActivity.recyclerView.adapter as MyAdapter).addAllItems(recipeArrayList)
            }
        })

    }

    companion object {

        val client: OkHttpClient
            get() {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                return OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build()
            }
    }
}


