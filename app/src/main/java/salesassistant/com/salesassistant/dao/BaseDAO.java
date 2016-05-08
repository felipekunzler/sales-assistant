package salesassistant.com.salesassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.data.Item;
import salesassistant.com.salesassistant.database.DatabaseHelper;

/**
 * Base data access object abstract class
 */
public abstract class BaseDAO<T extends Item> {

    private static final String TAG = BaseDAO.class.getSimpleName();
    public final String WHERE_ID_CLAUSE = getPkColumn() + " = ";

    private SQLiteDatabase db;

    /** Uses an existing database connection */
    public BaseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    /** Creates a new database connection */
    public BaseDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    protected abstract T extractItem(Cursor cursor);

    protected abstract ContentValues prepareItem(T item);

    protected abstract String getPkColumn();

    protected abstract String getTableName();

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
        Cursor cursor = db.query(getTableName(), null, null, null, null, null, getPkColumn());
        List<T> items = new ArrayList<>();
        while(cursor.moveToNext()) {
            items.add(extractItem(cursor));
        }
        return items;
    }

    public T getItem(long id) {
        Log.d(TAG, "getClients()");
        Cursor cursor = db.query(getTableName(), null, WHERE_ID_CLAUSE + id, null, null, null, getPkColumn());
        cursor.moveToFirst();
        return extractItem(cursor);
    }

    public int deleteItem(long id) {
        return db.delete(getTableName(), WHERE_ID_CLAUSE + id, null);
    }
}
