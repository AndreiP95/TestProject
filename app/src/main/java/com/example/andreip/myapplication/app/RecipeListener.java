package com.example.andreip.myapplication.app;

import java.util.List;

public interface RecipeListener {

    void onResponse(List<Recipe> response);

    void onError(String errorMessage);
}
