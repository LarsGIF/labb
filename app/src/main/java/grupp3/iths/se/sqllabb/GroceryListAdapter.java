package grupp3.iths.se.sqllabb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter <GroceryListAdapter.GroceryViewModel> {

    class GroceryViewModel extends RecyclerView.ViewHolder {
        private final TextView GroceryItemView;
        private final TextView GroceryQuantity;
        public RelativeLayout viewBackground, viewForeground;

        private GroceryViewModel(View itemView) {
            super(itemView);
            GroceryItemView = itemView.findViewById(R.id.textView);
            GroceryQuantity = itemView.findViewById(R.id.number_textView);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void removeItem (int position) {
        mGroceries.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem (Grocery grocery, int position) {
        mGroceries.add(position, grocery);
        notifyItemInserted(position);
    }

    private final LayoutInflater mInflater;
    private List<Grocery> mGroceries; // Cached copy of words

    public GroceryListAdapter(LayoutInflater mInflater, List<Grocery> mGroceries) {
        this.mInflater = mInflater;
        this.mGroceries = mGroceries;
    }

    GroceryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GroceryViewModel onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GroceryViewModel(itemView);
    }

    @Override
    public void onBindViewHolder(GroceryViewModel holder, int position) {


        if (mGroceries != null) {
            Grocery current = mGroceries.get(position);
            holder.GroceryItemView.setText(current.getmGroceryName());
            holder.GroceryQuantity.setText(String.valueOf(current.getmGroceryQuantity()));
        } else {
            // Covers the case of data not being ready yet.
            holder.GroceryItemView.setText("No Grocery");
            holder.GroceryQuantity.setText("No Quantity");
        }
    }

    void setWords(List<Grocery> groceries){
        mGroceries = groceries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mGroceries has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGroceries != null)
            return mGroceries.size();
        else return 0;
    }
}