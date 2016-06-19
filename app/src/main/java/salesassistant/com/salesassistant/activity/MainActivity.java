package salesassistant.com.salesassistant.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import salesassistant.com.salesassistant.Constants;
import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.database.DatabaseSyncTask;

/**
 * Main activity that only calls other activities.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        DatabaseSyncTask syncTask = new DatabaseSyncTask(this);
        if (id == R.id.action_backup) {
            syncTask.execute(Constants.SYNC_BACKUP);
            return true;
        }
        else if (id == R.id.action_restore) {
            syncTask.execute(Constants.SYNC_RESTORE);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
