package SQLData;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.andreip.myapplication.app.Recipe;

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
        mAllWords.observeForever(new Observer<List<RecipeDb>>() {
            @Override
            public void onChanged(@Nullable List<RecipeDb> recipeDbs) {
                if (mAllWords.getValue().equals(null)) {
                    // get RetroFit
                } else
                    Log.d("Get Words", mAllWords.getValue().get(0).getTitle());
            }
        });

        return mAllWords;
    }

    public void insertAllRecipes(List<Recipe> recipes) {
        for (Recipe recipe : recipes)
            insert(new RecipeDb(recipe));
    }

    public void insert(RecipeDb recipe) {
        new insertAsyncTask(mWordDao).execute(recipe);
    }

    private static class insertAsyncTask extends AsyncTask<RecipeDb, Void, Void> {

        private DataAccessWord mAsyncTaskDao;

        insertAsyncTask(DataAccessWord dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RecipeDb... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}