package salesassistant.com.salesassistant.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import salesassistant.com.salesassistant.Constants;

/**
 * Custom item click listener that calls an activity
 */
public class CustomOnItemClickListener implements ListView.OnItemClickListener {

    private Class cls;
    private Activity parentActivity;

    public CustomOnItemClickListener(Activity parentActivity, Class<?> cls) {
        this.parentActivity = parentActivity;
        this.cls = cls;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(parentActivity.getBaseContext(), cls);
        intent.putExtra(Constants.ITEM_ID_KEY, id);
        parentActivity.startActivity(intent);
    }
}
