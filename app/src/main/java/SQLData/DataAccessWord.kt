package SQLData

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

import android.arch.persistence.room.OnConflictStrategy.REPLACE

@Dao
interface DataAccessWord {

    @get:Query("SELECT * from recipe_table")
    val allWords: LiveData<List<RecipeDb>>

    @Insert(onConflict = REPLACE)
    fun insert(recipe: RecipeDb)

    @Query("DELETE FROM recipe_table")
    fun deleteAll()

    @Query("DELETE FROM recipe_table WHERE id = :id")
    fun delete(id: Int)
    //MutableLiveData + Update


}
