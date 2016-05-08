package salesassistant.com.salesassistant;

import android.widget.BaseAdapter;

import java.util.List;

import salesassistant.com.salesassistant.data.Item;

/**
 * Generic list view adapter
 */
public abstract class CustomAdapter<T extends Item> extends BaseAdapter {

    private List<T> data;

    public CustomAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

}
