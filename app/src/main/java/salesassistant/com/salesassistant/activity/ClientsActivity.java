package salesassistant.com.salesassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import salesassistant.com.salesassistant.CustomAdapter;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.listener.CustomOnItemClickListener;
import salesassistant.com.salesassistant.listener.CustomOnItemLongClickListener;

/**
 * Activity responsible for showing the list of clients.
 */
public class ClientsActivity extends AppCompatActivity {

    private static final String TAG = ClientsActivity.class.getSimpleName();
    private ListView listViewClients;
    private ClientDAO clientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        clientDAO = new ClientDAO(getBaseContext());

        listViewClients = (ListView) findViewById(R.id.listViewClients);
        listViewClients.setAdapter(new ClientAdapter());
        listViewClients.setOnItemClickListener(new CustomOnItemClickListener(this, ClientDetailsActivity.class));
        listViewClients.setOnItemLongClickListener(new CustomOnItemLongClickListener(this, clientDAO));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        onContentChanged();
    }

    @Override
    public void onContentChanged() {
        Log.d(TAG, "onContentChanged");
        if (clientDAO != null) {
            listViewClients.setAdapter(new ClientAdapter());
        }
    }

    public void onBtnNewClientClick(View view) {
        Intent intent = new Intent(getBaseContext(), ClientDetailsActivity.class);
        startActivity(intent);
    }

    /** List view client adapter */
    private class ClientAdapter extends CustomAdapter<Client> {

        public ClientAdapter() {
            super(clientDAO.getItems());
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.client_item, parent, false);
            }
            TextView txtClient = (TextView)convertView.findViewById(R.id.txtClientName);
            txtClient.setText(getItem(position).getName());

            return convertView;
        }
    }

}
