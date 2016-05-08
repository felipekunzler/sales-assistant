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
public class ClientDAO implements BaseDAO<Client>{

    private static final String TAG = ClientDAO.class.getSimpleName();

    public static final String TABLE_NAME = "CLIENT";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_ADDRESS = "ADDRESS";
    public static final String COL_EMAIL = "EMAIL";

    public static final String WHERE_ID_CLAUSE = COL_ID + " = ";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT NOT NULL," +
            COL_PHONE + " TEXT NOT NULL," +
            COL_ADDRESS + " TEXT NOT NULL," +
            COL_EMAIL + " TEXT NOT NULL);";

    private SQLiteDatabase db;

    /** Uses an existing database connection */
    public ClientDAO(SQLiteDatabase db) {
        this.db = db;
    }

    /** Creates a new database connection */
    public ClientDAO(Context context) {
        db = new DatabaseHelper(context).getWritableDatabase();
    }

    public long addItem(Client client) {
        ContentValues values = prepareClient(client);
        return db.insert(TABLE_NAME, null, values);
    }

    public void addItems(List<Client> clients) {
        for (Client client : clients) {
            addItem(client);
        }
    }

    public int updateItem(Client client) {
        ContentValues values = prepareClient(client);
        return db.update(TABLE_NAME, values, WHERE_ID_CLAUSE + client.getId(), null);
    }

    private ContentValues prepareClient(Client client) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, client.getName());
        values.put(COL_PHONE, client.getPhone());
        values.put(COL_ADDRESS, client.getAddress());
        values.put(COL_EMAIL, client.getEmail());
        return values;
    }

    public List<Client> getItems() {
        Log.d(TAG, "getClients()");
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_ID);
        List<Client> clients = new ArrayList<>();
        while(cursor.moveToNext()) {
            clients.add(extractClient(cursor));
        }
        return clients;
    }

    public Client getItem(long id) {
        Log.d(TAG, "getClients()");
        Cursor cursor = db.query(TABLE_NAME, null, WHERE_ID_CLAUSE + id, null, null, null, COL_ID);
        cursor.moveToFirst();
        return extractClient(cursor);
    }

    private Client extractClient(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String phone = cursor.getString(cursor.getColumnIndex(COL_PHONE));
        String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
        String address = cursor.getString(cursor.getColumnIndex(COL_ADDRESS));
        return new Client(id, name, phone, address, email);
    }

    public int deleteItem(long id) {
        return db.delete(TABLE_NAME, WHERE_ID_CLAUSE + id, null);
    }

}
