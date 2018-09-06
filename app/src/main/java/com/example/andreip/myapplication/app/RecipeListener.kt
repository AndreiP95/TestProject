package com.example.andreip.myapplication.app

interface RecipeListener {
    fun onResponse(response: List<Recipe>)

    fun onError(errorMessage: String)
}