package salesassistant.com.salesassistant.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;

/**
 * Backs up and restores the data base
 */
public final class DatabaseSyncTask extends AsyncTask<String, Void, Void> {

    private static final String DB_URL = "http://10.0.2.2:8080/SalesWebService/sales";
    private ProgressDialog pDialog;
    private Context context;

    public DatabaseSyncTask (Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(context.getString(R.string.processing));
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setIndeterminate(true);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url = new URL(DB_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (params[0].equals(Constants.SYNC_BACKUP)) {
                doBackup(conn);
            }
            else if (params[0].equals(Constants.SYNC_RESTORE)) {
                doRestore(conn);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private void doBackup(HttpURLConnection conn) throws IOException, JSONException {
        JSONArray clients = new ClientDAO(context).getItemsJSON();
        JSONArray products = new ProductDAO(context).getItemsJSON();
        JSONArray sales = new SaleDAO(context).getItemsJSON();

        JSONObject root = new JSONObject();
        root.put("clients", clients);
        root.put("products", products);
        root.put("sales", sales);

        conn.setRequestMethod("POST");
        BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
        out.write(root.toString().getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
        conn.getResponseCode();
    }

    private void doRestore(HttpURLConnection conn) throws IOException, JSONException {
        StringBuilder result = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        while((line = in.readLine()) != null) {
            result.append(line);
        }
        in.close();

        JSONObject json = new JSONObject(result.toString());
        new DatabaseHelper(context).restore(json);
    }

    @Override
    protected void onPostExecute(Void result) {
        pDialog.dismiss();
    }

}
