package grupp3.iths.se.sqllabb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class GroceryViewModel extends AndroidViewModel {

    private GroceryRepository mRepository;
    private LiveData<List<Grocery>> mAllGroceries;

    public GroceryViewModel(Application application) {
        super(application);
        mRepository = new GroceryRepository(application);
        mAllGroceries = mRepository.getAllGroceries();
    }

    public LiveData<List<Grocery>> getmAllGroceries() {
        return mAllGroceries;
    }

    public void insert(Grocery grocery) {
        mRepository.insert(grocery);
    }

    public void delete(Grocery grocery){
        mRepository.delete(grocery);
    }
    LiveData<List<Grocery>> getAllGroceries() {
        return mAllGroceries;
    }


}
