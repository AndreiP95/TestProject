package com.example.andreip.myapplication.app

import com.google.gson.annotations.SerializedName
import java.util.*

class RecipeResponse {
    @SerializedName("title")
    var title: String? = null
    @SerializedName("version")
    var version: String? = null
    @SerializedName("href")
    var href: String? = null
    @SerializedName("results")
    var recipes: List<Recipe> = ArrayList<Recipe> ()


}
