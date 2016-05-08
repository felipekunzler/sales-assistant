package salesassistant.com.salesassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Client;

public class SaleDetailsActivity extends AppCompatActivity {

    private SaleDAO saleDAO;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;

    private Spinner clientSpinner;
    private Spinner productSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_details);

        saleDAO = new SaleDAO(getBaseContext());
        clientDAO = new ClientDAO(getBaseContext());
        productDAO = new ProductDAO(getBaseContext());

        clientSpinner = (Spinner) findViewById(R.id.spinnerClient);
        productSpinner = (Spinner) findViewById(R.id.spinnerProduct);

        clientSpinner.setAdapter(createAdapter(clientDAO.getItems()));
        productSpinner.setAdapter(createAdapter(productDAO.getItems()));
    }

    private ArrayAdapter<?> createAdapter(List<?> data) {
        ArrayAdapter<?> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
