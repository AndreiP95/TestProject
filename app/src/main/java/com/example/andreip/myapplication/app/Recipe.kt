package com.example.andreip.myapplication.app

import SQLData.RecipeDb
import com.google.gson.annotations.SerializedName

class Recipe() {

    @SerializedName("title")
    private var title: String? = null
    @SerializedName("href")
    private var href: String? = null
    @SerializedName("ingredients")
    private var ingredients: String? = null
    @SerializedName("thumbnail")
    private var thumbnail: String? = null

    constructor (recipeDb: RecipeDb) : this() {
        this.title = recipeDb.title;
        this.href = recipeDb.href;
        this.ingredients = recipeDb.ingredients;
        this.thumbnail = recipeDb.thumbnail;

    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setHref(href: String) {
        this.href = href
    }

    fun getHref(): String? {
        return this.href
    }

    fun setIngredients(ingredients: String) {
        this.ingredients = ingredients
    }

    fun getIngredients(): String? {
        return this.ingredients
    }

    fun getThumbnail(): String? {
        return this.thumbnail
    }

    fun setThumbnail(thumbnail: String) {
        this.thumbnail = thumbnail
    }

}
