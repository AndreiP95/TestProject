package com.example.andreip.myapplication.app;

import com.google.gson.annotations.SerializedName;

public class RecipeResponse {

    private String title;
    @SerializedName("title")
    private String version;
    @SerializedName("version")
    private String href;
    @SerializedName("href")
    private ArrayList<Recipe> recipes;
    @SerializedName("results")

    private String image;
    private int values;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getValues() {
        return this.values;
    }

    public void setValues(int values) {
        this.values = values;
    }
}
