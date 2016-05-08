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
import salesassistant.com.salesassistant.dao.ProductDAO;
import salesassistant.com.salesassistant.data.Product;
import salesassistant.com.salesassistant.listener.CustomOnItemClickListener;
import salesassistant.com.salesassistant.listener.CustomOnItemLongClickListener;

public class ProductsActivity extends AppCompatActivity {

    private static final String TAG = ProductsActivity.class.getSimpleName();
    private ListView listViewProducts;
    private ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productDAO = new ProductDAO(getBaseContext());

        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        listViewProducts.setAdapter(new ProductAdapter());
        listViewProducts.setOnItemClickListener(new CustomOnItemClickListener(this, ProductDetailsActivity.class));
        listViewProducts.setOnItemLongClickListener(new CustomOnItemLongClickListener(this, productDAO));
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
        if (productDAO != null) {
            listViewProducts.setAdapter(new ProductAdapter());
        }
    }

    public void onBtnNewProductClick(View view) {
        Intent intent = new Intent(getBaseContext(), ProductDetailsActivity.class);
        startActivity(intent);
    }

    /** List view product adapter */
    private class ProductAdapter extends CustomAdapter<Product> {

        public ProductAdapter() {
            super(productDAO.getItems());
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.product_item, parent, false);

            TextView txtProduct = (TextView)view.findViewById(R.id.txtProductName);
            txtProduct.setText(getItem(position).getName());

            return view;
        }
    }
}
