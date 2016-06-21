package salesassistant.com.salesassistant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.data.Product;
import salesassistant.com.salesassistant.data.Sale;

/**
 * SalesAssistant database helper.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final int DB_VERSION = 20;
    private static final String DB_NAME = "salesassistant.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        createTables(db);
        importInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
    }

    public void restore(JSONObject json) throws JSONException {
        SQLiteDatabase db = getWritableDatabase();

        dropTables(db);
        createTables(db);

        ClientDAO clientDAO = new ClientDAO(db);
        JSONArray clients = json.getJSONArray("clients");
        for (int i = 0; i < clients.length(); i++) {
            clientDAO.addItem(new Client(clients.getJSONObject(i)));
        }

        ProductDAO productDAO = new ProductDAO(db);
        JSONArray products = json.getJSONArray("products");
        for (int i = 0; i < products.length(); i++) {
            productDAO.addItem(new Product(products.getJSONObject(i)));
        }

        SaleDAO saleDAO = new SaleDAO(db);
        JSONArray sales = json.getJSONArray("sales");
        for (int i = 0; i < sales.length(); i++) {
            saleDAO.addItem(new Sale(sales.getJSONObject(i)));
        }
    }

    public void createTables(SQLiteDatabase db) {
        db.execSQL(ClientDAO.CREATE_TABLE);
        db.execSQL(ProductDAO.CREATE_TABLE);
        db.execSQL(SaleDAO.CREATE_TABLE);
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL(ClientDAO.DROP_TABLE);
        db.execSQL(ProductDAO.DROP_TABLE);
        db.execSQL(SaleDAO.DROP_TABLE);
    }

    /**
     * Imports mock data for clients, products and sales.
     * @param db the database.
     */
    public void importInitialData(SQLiteDatabase db) {
        Client c1 = new Client(1, "Armazém do Pedro", "+555137890101", "Rua Independência 722", "bodegadopedro@bol.com.br");
        Client c2 = new Client(2, "Café do Parque", "+555188721313", "Av. João Pessoa 1892", "pqcafe@gmail.com");
        Client c3 = new Client(3, "Boteco Conceição", "+555199701233", "Rua da Conceição 123", "conceicaobar@uol.com.br");
        ClientDAO clientDAO = new ClientDAO(db);
        clientDAO.addItems(Arrays.asList(c1, c2, c3));

        Product p1 = new Product(1, "Palitos da Gina", "Gina CIA de Palitos LTDA");
        Product p2 = new Product(2, "Farinha de Trigo da Terra", "Moinho Vera Cruz S.A.");
        Product p3 = new Product(3, "Cerveja Golaço", "Bebitas Blerg LTDA");
        Product p4 = new Product(4, "Leite da Vaca", "Laticínios Fazenda Belo Brejo LTDA");
        Product p5 = new Product(5, "Arroz Tio Ubirajara", "Cereais Ubirajara S.A.");
        Product p6 = new Product(6, "Caninha 29", "Alambique do Pedro LTDA");
        ProductDAO productDAO = new ProductDAO(db);
        productDAO.addItems(Arrays.asList(p1, p2, p3, p4, p5, p6));

        Sale s1 = new Sale(c1, p1);
        Sale s2 = new Sale(c2, p2);
        SaleDAO saleDAO = new SaleDAO(db);
        saleDAO.addItems(Arrays.asList(s1, s2));
    }

}
