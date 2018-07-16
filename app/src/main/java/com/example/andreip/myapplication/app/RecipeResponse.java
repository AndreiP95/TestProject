package com.example.andreip.myapplication.app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeResponse {
    @SerializedName("title")
    private String title;
    @SerializedName("version")
    private String version;
    @SerializedName("href")
    private String href;
    @SerializedName("results")
    private ArrayList<Recipe> recipes;


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

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
