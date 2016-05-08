package salesassistant.com.salesassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.database.DatabaseHelper;

/**
 * Client Data Access Object
 */
public class ClientDAO extends BaseDAO<Client>{

    private static final String TAG = ClientDAO.class.getSimpleName();

    public static final String TABLE_NAME = "CLIENT";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_EMAIL = "EMAIL";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT NOT NULL," +
            COL_PHONE + " TEXT NOT NULL," +
            COL_ADDRESS + " TEXT NOT NULL," +
            COL_EMAIL + " TEXT NOT NULL);";

    /** Uses an existing database connection */
    public ClientDAO(SQLiteDatabase db) {
        super(db);
    }

    /** Creates a new database connection */
    public ClientDAO(Context context) {
        super(context);
    }

    @Override
    protected Client extractItem(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String phone = cursor.getString(cursor.getColumnIndex(COL_PHONE));
        String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
        String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
        return new Client(id, name, phone, address, email);
    }

    @Override
    protected ContentValues prepareItem(Client client) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, client.getName());
        values.put(COL_PHONE, client.getPhone());
        values.put(COL_ADDRESS, client.getAddress());
        values.put(COL_EMAIL, client.getEmail());
        return values;
    }

    @Override
    protected String getPkColumn() {
        return COL_ID;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

}
