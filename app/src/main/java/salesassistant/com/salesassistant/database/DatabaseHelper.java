package salesassistant.com.salesassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;

import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Client;

/**
 * SalesAssitant database helper
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "salesassistant.db";
    private static final String CREATE_DB = ClientDAO.CREATE_TABLE + ProductDAO.CREATE_TABLE + SaleDAO.CREATE_TABLE;
    private static final String DROP_DB = String.format("DROP TABLE IF EXISTS %s, %s, %s",
            ClientDAO.TABLE_NAME, ProductDAO.TABLE_NAME, SaleDAO.TABLE_NAME);

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
        importInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_DB);
        onCreate(db);
    }

    public void importInitialData(SQLiteDatabase db) {
        Client c1 = new Client("Armazém do Pedro", "+555137890101", "Rua Independência 722", "bodegadopedro@bol.com.br");
        Client c2 = new Client("Café do Parque", "+555188721313", "Av. João Pessoa 1892", "pqcafe@gmail.com");
        Client c3 = new Client("Boteco Conceição", "+555199701233", "Rua da Conceição 123", "conceicaobar@uol.com.br");

        ClientDAO clientDAO = new ClientDAO(db);
        clientDAO.addClients(Arrays.asList(c1, c2, c3));
    }

}
