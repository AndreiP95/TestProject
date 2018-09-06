package com.example.andreip.myapplication.app


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import SQLData.RecipeDb

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private val recipeArrayList: ArrayList<Recipe>


    fun refreshAllItems(items: ArrayList<Recipe>) {
        recipeArrayList.clear()
        addAllItems(items)

    }

    fun setDbArrayList(recipeDbList: List<RecipeDb>) {

        recipeArrayList.clear()
        for (recipeDb in recipeDbList)
            recipeArrayList.add(Recipe(recipeDb))

        notifyDataSetChanged()
    }

    fun addAllItems(items: List<Recipe>) {
        recipeArrayList.addAll(items)
        notifyDataSetChanged()
    }


     class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var ingredients: TextView
        var title: TextView
        var imageRecipe: ImageView

        init {
            ingredients = v.findViewById(R.id.TVIngredients)
            title = v.findViewById(R.id.TVTitle)
            imageRecipe = v.findViewById(R.id.IVRecept)
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val recipe = recipeArrayList[position]
        holder.title.text = recipe.getTitle()!!.replace("\n".toRegex(), "")
        holder.ingredients.text = recipe.getIngredients()

        Log.d("TITLE + INGREDIENTS", recipe.getTitle() + " " + recipe.getIngredients())

        if (recipe.getThumbnail() == null || recipe.getThumbnail()!!.equals("", ignoreCase = true))
            return

        Picasso.get()
                .load(recipe.getThumbnail())
                .resize(60, 60)
                .centerCrop()
                .into(holder.imageRecipe)

    }

    init {
        this.recipeArrayList = ArrayList()
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.ViewHolder {
        // create a new view
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_recyclerview, parent, false)

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return recipeArrayList.size
    }
}
