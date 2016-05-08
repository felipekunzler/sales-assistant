package salesassistant.com.salesassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Client;

/**
 * SalesAssitant database helper
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "salesassistant.db";
    private static final String CREATE_DB = ClientDAO.CREATE_TABLE + ProductDAO.CREATE_TABLE + SaleDAO.CREATE_TABLE;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS %s;";
    private static final String DROP_DB = String.format(DROP_TABLE + DROP_TABLE + DROP_TABLE,
            ClientDAO.TABLE_NAME, ProductDAO.TABLE_NAME, SaleDAO.TABLE_NAME);

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
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
        List<Client> clients = new ArrayList<>(Arrays.asList(c1, c2, c3));
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);
        clients.add(c1);

        ClientDAO clientDAO = new ClientDAO(db);
        clientDAO.addItems(clients);
    }

}
