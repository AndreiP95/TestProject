package SQLData

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.example.andreip.myapplication.app.Recipe

@Entity(tableName = "recipe_table")
class RecipeDb {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "title")
    var title: String? = null

    @ColumnInfo(name = "href")
    var href: String? = null

    @ColumnInfo(name = "ingredients")
    var ingredients: String? = null

    @ColumnInfo(name = "thumbnail")
    var thumbnail: String? = null

    constructor() {

    }

    constructor(recipe: Recipe) {
        this.title = recipe.getTitle()
        this.href = recipe.getHref()
        this.ingredients = recipe.getIngredients()
        this.thumbnail = recipe.getThumbnail()
    }
}