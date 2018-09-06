package SQLData

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log

import com.example.andreip.myapplication.app.Recipe

import java.util.ArrayList

class WordRepository(application: Application) {

    private val mWordDao: DataAccessWord?
     val allWords: LiveData<List<RecipeDb>>


    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db?.wordDao()
        allWords = mWordDao!!.allWords
    }

    fun insertAllRecipes(recipes: List<Recipe>) {
        val recipeDbArrayList = ArrayList<RecipeDb>()
        Log.d("DEBUG", recipes.size.toString() + " ")
        for (recipe in recipes) {
            Log.d("DEBUG", recipe.getTitle()!! + " ")

            recipeDbArrayList.add(RecipeDb(recipe))
        }
        insert(recipeDbArrayList)
    }

    fun getValueAllWords() : List<RecipeDb>
    {
        return allWords.value!!
    }

    fun insert(recipe: List<RecipeDb>?) {

        if (recipe == null) {
            Log.d("ERROR", "No data to insert ")
            return
        }

        insertAsyncTask(mWordDao).execute(recipe)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: DataAccessWord?) : AsyncTask<List<RecipeDb>, Void, Void>() {

        override fun doInBackground(vararg params: List<RecipeDb>): Void? {

            mAsyncTaskDao?.deleteAll()
            for (recipeDb in params[0])
                mAsyncTaskDao?.insert(recipeDb)
            return null
        }
    }
}