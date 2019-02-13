package grupp3.iths.se.sqllabb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GroceryDao {

    @Insert
    void insert(Grocery grocery);

    @Delete
    void delete(Grocery grocery);

    @Query("DELETE FROM grocery_table")
    void deleteAll();

    @Query("SELECT * from grocery_table ORDER BY mGroceryName ASC")
    LiveData<List<Grocery>> getAllGroceries();

    @Query("SELECT * from grocery_table ORDER BY mGroceryQuantity ASC")
    LiveData<List<Grocery>> getAllGroceriesQuantity();
}
