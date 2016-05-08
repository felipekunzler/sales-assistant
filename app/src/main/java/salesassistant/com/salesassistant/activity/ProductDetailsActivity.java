package salesassistant.com.salesassistant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.ClientDAO;
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.data.Client;
import salesassistant.com.salesassistant.data.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editCompany;

    private long productId;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productDAO = new ProductDAO(getBaseContext());

        editName = (EditText) findViewById(R.id.editName);
        editCompany = (EditText) findViewById(R.id.editCompany);

        productId = getIntent().getLongExtra(Constants.ITEM_ID_KEY, -1);
        if (productId != -1) {
            setTitle(getString(R.string.product_edit));

            Product product = productDAO.getItem(productId);
            editName.setText(product.getName());
            editCompany.setText(product.getCompany());
        }
        else {
            setTitle(getString(R.string.product_new_title));
        }
    }

    public void onBtnSaveProductClick(View view) {
        Product product = new Product();
        product.setName(editName.getText().toString());
        product.setCompany(editCompany.getText().toString());
        product.setId(productId);

        if (product.getId() == -1) {
            productDAO.addItem(product);
        }
        else {
            productDAO.updateItem(product);
        }

        finish();
    }
}
