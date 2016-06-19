package salesassistant.com.salesassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.adapter.CustomAdapter;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.data.Item;
import salesassistant.com.salesassistant.data.Product;
import salesassistant.com.salesassistant.data.Sale;

/**
 * Activity used to both create and show a Sale.
 */
public class SaleDetailsActivity extends AppCompatActivity {

    private SaleDAO saleDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;

    private Spinner clientSpinner;
    private Spinner productSpinner;
    private TextView txtClientName;
    private TextView txtClientPhone;
    private TextView txtClientEmail;
    private TextView txtClientAddress;
    private TextView txtProductName;
    private TextView txtProductCompany;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_details);

        saleDAO = new SaleDAO(getBaseContext());
        clientDAO = new ClientDAO(getBaseContext());
        productDAO = new ProductDAO(getBaseContext());

        clientSpinner = (Spinner) findViewById(R.id.spinnerClient);
        productSpinner = (Spinner) findViewById(R.id.spinnerProduct);
        txtClientName = (TextView) findViewById(R.id.txtClientName);
        txtClientEmail = (TextView) findViewById(R.id.txtClientEmail);
        txtClientAddress = (TextView) findViewById(R.id.txtClientAddress);
        txtClientPhone = (TextView) findViewById(R.id.txtClientPhone);
        txtProductCompany = (TextView) findViewById(R.id.txtProductCompany);
        txtProductName = (TextView) findViewById(R.id.txtProductName);
        btnSave = (Button) findViewById(R.id.btnSaveSale);

        final CustomSpinnerAdapter<Client> clientAdapter = new CustomSpinnerAdapter<>(clientDAO.getItems());
        clientSpinner.setAdapter(clientAdapter);
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Client client = clientAdapter.getItem(position);
                txtClientName.setText(client.getName());
                txtClientEmail.setText(client.getEmail());
                txtClientAddress.setText(client.getAddress());
                txtClientPhone.setText(client.getPhone());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final CustomSpinnerAdapter<Product> productAdapter = new CustomSpinnerAdapter<>(productDAO.getItems());
        productSpinner.setAdapter(productAdapter);
        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Product product = productAdapter.getItem(position);
                txtProductName.setText(product.getName());
                txtProductCompany.setText(product.getCompany());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        long saleId = getIntent().getLongExtra(Constants.ITEM_ID_KEY, -1);
        if (saleId != -1) {
            setTitle(getString(R.string.sale_details));
            btnSave.setVisibility(View.INVISIBLE);
            clientSpinner.setEnabled(false);
            productSpinner.setEnabled(false);

            Sale sale = saleDAO.getItem(saleId);
            int selectClient = clientAdapter.getPosition(sale.getClient().getId());
            clientSpinner.setSelection(selectClient);
            int selectedProduct = productAdapter.getPosition(sale.getProduct().getId());
            productSpinner.setSelection(selectedProduct);
        }
        else {
            setTitle(R.string.sale_new);
        }
    }

    public void onBtnSaveSaleClick(View view) {
        Client client = (Client) clientSpinner.getSelectedItem();
        Product product = (Product) productSpinner.getSelectedItem();
        saleDAO.addItem(new Sale(client, product));
        finish();
    }

    /** Custom spinner adapter used to select an Item  */
    private class CustomSpinnerAdapter<T extends Item> extends CustomAdapter<T> {

        public CustomSpinnerAdapter(List<T> data) {
            super(data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            }
            TextView txt = (TextView) convertView.findViewById(android.R.id.text1);
            txt.setText(getItem(position).toString());
            return convertView;
        }

        /**
         * Gets the position of an {@link Item} given its {#link Item.getId()}.
         * @param id the item's id
         * @return the item's position. Or, -1 if the item's id wasn't found.
         */
        public int getPosition(long id) {
            for (int pos = 0; pos < data.size(); pos++) {
                if (getItem(pos).getId() == id) {
                    return pos;
                }
            }
            return -1;
        }
    }

}
