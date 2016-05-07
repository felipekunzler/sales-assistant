package salesassistant.com.salesassistant.dao;

/**
 * Product Data Access Object
 */
public class ProductDAO {

    public static final String TABLE_NAME = "PRODUCT";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_COMPANY = "COMPANY";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT NOT NULL," +
            COL_COMPANY + " TEXT NOT NULL);";
    
}
