package salesassistant.com.salesassistant.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.SaleDAO;

public class SalesActivity extends AppCompatActivity {

    private SaleDAO saleDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        saleDAO = new SaleDAO(getBaseContext());
    }

}
