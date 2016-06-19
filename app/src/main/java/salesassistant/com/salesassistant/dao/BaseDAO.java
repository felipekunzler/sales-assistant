package salesassistant.com.salesassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import salesassistant.com.salesassistant.data.Item;
import salesassistant.com.salesassistant.database.DatabaseHelper;

/**
 * Generic abstract data access object for Items
 */
public abstract class BaseDAO<T extends Item> {

    private static final String TAG = BaseDAO.class.getSimpleName();
    public final String WHERE_ID_CLAUSE = getPkColumn() + " = ";

    protected SQLiteDatabase db;

    /** Uses an existing database connection */
    public BaseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    /** Creates a new database connection */
    public BaseDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    protected abstract String getPkColumn();

    protected abstract String getTableName();

    /**
     * Prepares an item into a {@link ContentValues}
     * @param item the item
     * @return the content value
     */
    protected abstract ContentValues prepareItem(T item);

    /**
     * Extracts an item from a {@link Cursor}
     * @param cursor the cursor
     * @return the extracted item
     */
    protected abstract T extractItem(Cursor cursor);

    public long addItem(T item) {
        ContentValues values = prepareItem(item);
        return db.insert(getTableName(), null, values);
    }

    public void addItems(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    public int updateItem(T item) {
        ContentValues values = prepareItem(item);
        return db.update(getTableName(), values, WHERE_ID_CLAUSE + item.getId(), null);
    }

    public List<T> getItems() {
        Log.d(TAG, "getItems()");
        Cursor cursor = getCursor(null);
        List<T> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            items.add(extractItem(cursor));
        }
        return items;
    }

    public T getItem(long id) {
        Log.d(TAG, "getItem()");
        Cursor cursor = getCursor(WHERE_ID_CLAUSE + id);
        cursor.moveToFirst();
        return extractItem(cursor);
    }

    protected Cursor getCursor(String whereClause) {
        return db.query(getTableName(), null, whereClause, null, null, null, getPkColumn());
    }

    public int deleteItem(long id) {
        return db.delete(getTableName(), WHERE_ID_CLAUSE + id, null);
    }

    public JSONArray getItemsJSON() {
        JSONArray jsonArray = new JSONArray();
        for (Item item : getItems()) {
            jsonArray.put(item.toJSON());
        }
        return jsonArray;
    }

}
