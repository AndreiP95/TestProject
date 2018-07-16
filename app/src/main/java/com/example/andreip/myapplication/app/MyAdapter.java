package com.example.andreip.myapplication.app;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Recipe> recipeArrayList;

    public void addAllItems(ArrayList<Recipe> items) {
        recipeArrayList.addAll(items);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView ingredients, title;
        ImageView imageRecipe;

        public ViewHolder(View v) {
            super(v);
            ingredients = (TextView) v.findViewById(R.id.TVIngredients);
            title = (TextView) v.findViewById(R.id.TVTitle);
            imageRecipe = (ImageView) v.findViewById(R.id.IVRecept);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Recipe recipe = recipeArrayList.get(position);
        holder.title.setText(recipe.getTitle());
        holder.ingredients.setText(recipe.getIngredients());
        try {
            Picasso.get()
                    .load(recipe.getThumbnail())
                    .resize(60, 60)
                    .centerCrop()
                    .into(holder.imageRecipe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public MyAdapter() {
        this.recipeArrayList = new ArrayList<>();
    }


    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recyclerview, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
