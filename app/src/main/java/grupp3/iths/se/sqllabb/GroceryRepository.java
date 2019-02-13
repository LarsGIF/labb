package grupp3.iths.se.sqllabb;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class GroceryRepository {

    private GroceryDao mGroceryDao;
    private LiveData<List<Grocery>> mAllGroceries;

    GroceryRepository(Application application) {
        GroceryRoomDatabase db = GroceryRoomDatabase.getDatabase(application);
        mGroceryDao = db.groceryDao();
        mAllGroceries = mGroceryDao.getAllGroceries();
    }

    LiveData<List<Grocery>> getAllGroceries() {
        return mAllGroceries;
    }

    public void insert (Grocery grocery) {
        new insertAsyncTask(mGroceryDao).execute(grocery);
    }

    public void delete(Grocery grocery){
        new deleteAsyncTask(mGroceryDao).execute(grocery);
    }

    private static class insertAsyncTask extends android.os.AsyncTask<Grocery, Void, Void> {

        private GroceryDao mAsyncTaskDao;

        insertAsyncTask(GroceryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Grocery... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Grocery, Void, Void> {

        private GroceryDao mAsyncTaskDao;

        deleteAsyncTask(GroceryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Grocery... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }


    public GroceryDao getGroceryDao(){
        return mGroceryDao;
    }

}
