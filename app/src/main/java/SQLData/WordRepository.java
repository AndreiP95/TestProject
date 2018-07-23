package SQLData;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.andreip.myapplication.app.Recipe;

import java.util.ArrayList;
import java.util.List;

public class WordRepository {

    private DataAccessWord mWordDao;
    private LiveData<List<RecipeDb>> mAllWords;


    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<RecipeDb>> getAllWords() {
        return mAllWords;
    }

    public void insertAllRecipes(List<Recipe> recipes) {
        ArrayList<RecipeDb> recipeDbArrayList = new ArrayList<>();
        Log.d("DEBUG", recipes.size() + " ");
        for (Recipe recipe : recipes) {
            Log.d("DEBUG", recipe.getTitle() + " ");

            recipeDbArrayList.add(new RecipeDb(recipe));
        }
        insert(recipeDbArrayList);
    }

    public void insert(List<RecipeDb> recipe) {
        new insertAsyncTask(mWordDao).execute(recipe);
    }

    private static class insertAsyncTask extends AsyncTask<List<RecipeDb>, Void, Void> {

        private DataAccessWord mAsyncTaskDao;

        insertAsyncTask(DataAccessWord dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<RecipeDb>... params) {
            mAsyncTaskDao.deleteAll();
            for (RecipeDb recipeDb : params[0])
                mAsyncTaskDao.insert(recipeDb);
            return null;
        }
    }
}