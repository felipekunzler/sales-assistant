package salesassistant.com.salesassistant.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.data.Product;
import salesassistant.com.salesassistant.data.Sale;

/**
 * Sale Data Access Object
 */
public class SaleDAO extends BaseDAO<Sale> {

    public static final String TABLE_NAME = "SALE";
    public static final String COL_ID = "S_ID";
    public static final String COL_CLIENT = "S_CLIENT";
    public static final String COL_PRODUCT = "S_PRODUCT";
    public static final String COL_DATE = "S_SALE_DATE";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
            COL_CLIENT + " INTEGER NOT NULL," +
            COL_PRODUCT + " INTEGER NOT NULL," +
            "FOREIGN KEY(" + COL_CLIENT + ") REFERENCES " + ClientDAO.TABLE_NAME + "(" + ClientDAO.COL_ID + ")," +
            "FOREIGN KEY(" + COL_PRODUCT + ") REFERENCES " + ProductDAO.TABLE_NAME + "(" + ProductDAO.COL_ID + "));";

    private static final String QUERY_SALES = String.format(
            "SELECT * FROM %1$s S " +
            "INNER JOIN %2$s C ON S.%3$s = C.%4$s " +
            "INNER JOIN %5$s P ON S.%6$s = P.%7$s ",
            TABLE_NAME, ClientDAO.TABLE_NAME, COL_CLIENT, ClientDAO.COL_ID,
            ProductDAO.TABLE_NAME, COL_PRODUCT, ProductDAO.COL_ID);

    /** Uses an existing database connection */
    public SaleDAO(SQLiteDatabase db) {
        super(db);
    }

    /** Creates a new database connection */
    public SaleDAO(Context context) {
        super(context);
    }

    @Override
    protected Sale extractItem(Cursor cursor) {
        Client client = ClientDAO.extractItemUtil(cursor);
        Product product = ProductDAO.extractItemUtil(cursor);

        Sale sale = new Sale(client, product);
        sale.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        sale.setDate(parseDate(cursor.getString(cursor.getColumnIndex(COL_DATE))));

        return sale;
    }

    /**
     * Pareses a SQLite string date to a {@link Date}
     * @param strDate the date
     * @return the {@link Date}
     */
    private static Date parseDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_ISO8601);
        dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.TIME_ZONE_ISO8601));
        Date date;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
        return date;
    }

    @Override
    protected ContentValues prepareItem(Sale item) {
        ContentValues values = new ContentValues();
        values.put(COL_CLIENT, item.getClient().getId());
        values.put(COL_PRODUCT, item.getProduct().getId());
        return values;
    }

    @Override
    protected Cursor getCursor(String whereClause) {
        String query = whereClause == null ? QUERY_SALES : QUERY_SALES + " WHERE " + whereClause;
        return db.rawQuery(query, null);
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
