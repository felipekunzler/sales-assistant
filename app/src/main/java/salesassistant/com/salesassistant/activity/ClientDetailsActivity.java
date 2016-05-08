package salesassistant.com.salesassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.data.Client;

public class ClientDetailsActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPhone;
    private EditText editAddress;
    private EditText editEmail;

    private long clientId;
    private ClientDAO clientDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        clientDAO = new ClientDAO(getBaseContext());

        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editEmail = (EditText) findViewById(R.id.editEmail);

        clientId = getIntent().getLongExtra(Constants.ITEM_ID_KEY, -1);
        if (clientId != -1) {
            setTitle(getString(R.string.client_edit));

            Client client = clientDAO.getItem(clientId);
            editName.setText(client.getName());
            editPhone.setText(client.getPhone());
            editAddress.setText(client.getAddress());
            editEmail.setText(client.getEmail());
        }
        else {
            setTitle(getString(R.string.client_new));
        }
    }

    public void onBtnSaveClientClick(View view) {
        Client client = new Client();
        client.setName(editName.getText().toString());
        client.setAddress(editAddress.getText().toString());
        client.setEmail(editEmail.getText().toString());
        client.setPhone(editPhone.getText().toString());
        client.setId(clientId);

        if (client.getId() == -1) {
            clientDAO.addItem(client);
        }
        else {
            clientDAO.updateItem(client);
        }

        finish();
    }

}
