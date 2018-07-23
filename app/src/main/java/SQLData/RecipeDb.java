package SQLData;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.andreip.myapplication.app.Recipe;

@Entity(tableName = "recipe_table")
public class RecipeDb {

    public RecipeDb() {

    }

    public RecipeDb(Recipe recipe) {
        this.title = recipe.getTitle();
        this.href = recipe.getHref();
        this.ingredients = recipe.getIngredients();
        this.thumbnail = recipe.getThumbnail();
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "href")
    private String href;

    @ColumnInfo(name = "ingredients")
    private String ingredients;

    @ColumnInfo(name = "thumbnail")
    private String thumbnail;


    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getHref() {
        return href;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}