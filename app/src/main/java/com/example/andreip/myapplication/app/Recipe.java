package com.example.andreip.myapplication.app;

import com.google.gson.annotations.SerializedName;

import SQLData.RecipeDb;

public class Recipe {

    @SerializedName("title")
    private String title;
    @SerializedName("href")
    private String href;
    @SerializedName("ingredients")
    private String ingredients;
    @SerializedName("thumbnail")
    private String thumbnail;

    public Recipe(RecipeDb recipeDb) {
        this.title = recipeDb.getTitle();
        this.href = recipeDb.getHref();
        this.ingredients = recipeDb.getIngredients();
        this.thumbnail = recipeDb.getThumbnail();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHref() {
        return this.href;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return this.ingredients;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
