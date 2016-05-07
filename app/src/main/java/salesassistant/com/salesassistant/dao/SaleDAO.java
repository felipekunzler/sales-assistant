package salesassistant.com.salesassistant.dao;

/**
 * Sale Data Access Object
 */
public class SaleDAO {

    public static final String TABLE_NAME = "SALE";

    public static final String COL_ID = "ID";
    public static final String COL_CLIENT = "CLIENT";
    public static final String COL_PRODUCT = "PRODUCT";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CLIENT + "INTEGER NOT NULL," +
            COL_PRODUCT + "INTEGER NOT NULL," +
            "FOREIGN KEY(" + COL_CLIENT + ") REFERENCES " + ClientDAO.TABLE_NAME + "(" + ClientDAO.COL_ID + ")," +
            "FOREIGN KEY(" + COL_PRODUCT + ") REFERENCES " + ProductDAO.TABLE_NAME + "(" + ProductDAO.COL_ID + "));";

}
