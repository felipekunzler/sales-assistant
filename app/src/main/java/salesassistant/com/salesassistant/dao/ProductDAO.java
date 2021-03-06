package salesassistant.com.salesassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import salesassistant.com.salesassistant.data.Product;

/**
 * Product Data Access Object
 */
public class ProductDAO extends BaseDAO<Product>{

    public static final String TABLE_NAME = "PRODUCT";
    public static final String COL_ID = "P_ID";
    public static final String COL_NAME = "P_NAME";
    public static final String COL_COMPANY = "P_COMPANY";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT NOT NULL," +
            COL_COMPANY + " TEXT NOT NULL);";

    /** Uses an existing database connection */
    public ProductDAO(SQLiteDatabase db) {
        super(db);
    }

    /** Creates a new database connection */
    public ProductDAO(Context context) {
        super(context);
    }

    @Override
    protected Product extractItem(Cursor cursor) {
        return ProductDAO.extractItemUtil(cursor);
    }

    static Product extractItemUtil(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
        String company = cursor.getString(cursor.getColumnIndex(COL_COMPANY));
        return new Product(id, name, company);
    }

    @Override
    protected ContentValues prepareItem(Product product) {
        ContentValues values = new ContentValues();
        if (product.getId() != -1){
            values.put(COL_ID, product.getId());
        }
        values.put(COL_NAME, product.getName());
        values.put(COL_COMPANY, product.getCompany());
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
