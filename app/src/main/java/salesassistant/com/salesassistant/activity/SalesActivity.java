package salesassistant.com.salesassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

import salesassistant.com.salesassistant.adapter.CustomAdapter;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.SaleDAO;
import salesassistant.com.salesassistant.data.Sale;
import salesassistant.com.salesassistant.listener.CustomOnItemClickListener;

/**
 * Activity responsible for listing existing sales
 */
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
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.sale_item, parent, false);
            }
            TextView txtClient = (TextView) convertView.findViewById(R.id.txtClientName);
            TextView txtProduct = (TextView) convertView.findViewById(R.id.txtProductName);
            TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);

            txtClient.setText(getItem(position).getClient().getName());
            txtProduct.setText(getItem(position).getProduct().getName());

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getDefault());
            String prettyDate = dateFormat.format(getItem(position).getDate());
            txtDate.setText(prettyDate);

            return convertView;
        }
    }

}
