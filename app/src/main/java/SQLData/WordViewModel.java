package SQLData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<RecipeDb>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<RecipeDb>> getAllWords() {
        return mAllWords;
    }

   // public void insert(RecipeDb recipe) {
  //      mRepository.insert(recipe);
  //  }
}