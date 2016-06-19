package salesassistant.com.salesassistant.adapter;

import android.widget.BaseAdapter;

import java.util.List;

import salesassistant.com.salesassistant.data.Item;

/**
 * Generic list view adapter
 */
public abstract class CustomAdapter<T extends Item> extends BaseAdapter {

    protected List<T> data;

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
