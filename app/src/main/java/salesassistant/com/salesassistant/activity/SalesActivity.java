package salesassistant.com.salesassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import salesassistant.com.salesassistant.CustomAdapter;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Sale;
import salesassistant.com.salesassistant.listener.CustomOnItemClickListener;

public class SalesActivity extends AppCompatActivity {

    private ListView listViewSales;
    private SaleDAO saleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        saleDAO = new SaleDAO(getBaseContext());

        listViewSales = (ListView) findViewById(R.id.listViewSales);
        listViewSales.setAdapter(new SaleAdapter());
        listViewSales.setOnItemClickListener(new CustomOnItemClickListener(this, SaleDetailsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        onContentChanged();
    }

    @Override
    public void onContentChanged() {
        if (saleDAO != null) {
            listViewSales.setAdapter(new SaleAdapter());
        }
    }

    public void onBtnNewSaleClick(View view) {
        Intent intent = new Intent(getBaseContext(), SaleDetailsActivity.class);
        startActivity(intent);
    }

    /** List view sales adapter */
    private class SaleAdapter extends CustomAdapter<Sale> {

        public SaleAdapter() {
            super(saleDAO.getItems());
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.sale_item, parent, false);

            TextView txtClient = (TextView) view.findViewById(R.id.txtClientName);
            TextView txtProduct = (TextView) view.findViewById(R.id.txtProductName);
            TextView txtDate = (TextView) view.findViewById(R.id.txtDate);

            txtClient.setText(getItem(position).getClient().getName());
            txtProduct.setText(getItem(position).getProduct().getName());
            txtDate.setText(getItem(position).getDate().toString());

            return view;
        }
    }

}
