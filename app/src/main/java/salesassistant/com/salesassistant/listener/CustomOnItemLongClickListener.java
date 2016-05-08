package salesassistant.com.salesassistant.listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import salesassistant.com.salesassistant.R;
import salesassistant.com.salesassistant.dao.BaseDAO;

/**
 * Custom long item click listener that confirms if ok to delete item
 */
public class CustomOnItemLongClickListener implements ListView.OnItemLongClickListener {

    private BaseDAO baseDAO;
    private Activity parentActivity;

    public CustomOnItemLongClickListener(Activity parentActivity, BaseDAO baseDAO) {
        this.parentActivity = parentActivity;
        this.baseDAO = baseDAO;
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, final long id) {
        AlertDialog.Builder confirmAlert = new AlertDialog.Builder(parentActivity);
        confirmAlert.setTitle(R.string.alert_delete_confirmation);
        confirmAlert.setMessage(R.string.alert_delete_message);

        confirmAlert.setPositiveButton(R.string.alert_delete_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                baseDAO.deleteItem(id);
                parentActivity.onContentChanged();
                Toast.makeText(parentActivity, R.string.toast_deleted, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        confirmAlert.setNegativeButton(R.string.alert_cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        confirmAlert.show();
        return true;
    }

}
