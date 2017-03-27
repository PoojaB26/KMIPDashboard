package poojab26.kmipdashboard.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 26-Mar-17.
 */

public class ContactEdit extends Activity implements View.OnClickListener {

    private Button save;
    private String mode;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail_page);

        // get the values passed to the activity from the calling activity
        // determine the mode - add, update or delete
        if (this.getIntent().getExtras() != null){
            Bundle bundle = this.getIntent().getExtras();
            mode = bundle.getString("mode");
        }

        // get references to the buttons and attach listeners
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.save:
                ContentValues values = new ContentValues();
                values.put(ContactsDb.KEY_TIMESTAMP, 333);
                values.put(ContactsDb.KEY_THREAD, "h");
                values.put(ContactsDb.KEY_CLASSNAME, "j");
                values.put(ContactsDb.KEY_FUNCTION, "k");
                values.put(ContactsDb.KEY_LOGLEVEL, "j");
                values.put(ContactsDb.KEY_LOGW, "k");
                getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                finish();
                break;



        }
    }

    // this sets the spinner selection based on the value
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

}
