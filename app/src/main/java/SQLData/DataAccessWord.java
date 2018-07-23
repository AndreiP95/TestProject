package SQLData;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface DataAccessWord {

    @Insert(onConflict = REPLACE)
    void insert(RecipeDb recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAll();

    @Query("SELECT * from recipe_table ORDER BY title ASC")
    LiveData<List<RecipeDb>> getAllWords();

    @Query("DELETE FROM recipe_table WHERE id = :id")
    void delete(int id);
    //MutableLiveData + Update
}
