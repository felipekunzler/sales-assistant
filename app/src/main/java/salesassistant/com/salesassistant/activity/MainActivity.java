package salesassistant.com.salesassistant.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import salesassistant.com.salesassistant.R;

/**
 * Main activity that only calls other activities.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    public void onBtnClientsClick(View view) {
        startCustomActivity(ClientsActivity.class);
    }

    public void onBtnProductsClick(View view) {
        startCustomActivity(ProductsActivity.class);
    }

    public void onBtnSalesClick(View view) {
        startCustomActivity(SalesActivity.class);
    }

    private void startCustomActivity(Class<?> clazz){
        Intent intent = new Intent(getBaseContext(), clazz);
        startActivity(intent);
    }

}
