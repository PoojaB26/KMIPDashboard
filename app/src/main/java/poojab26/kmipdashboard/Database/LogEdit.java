package poojab26.kmipdashboard.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by pblead26 on 26-Mar-17.
 */

public class LogEdit extends Activity {

    private Button save;
    private String mode;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentValues values = new ContentValues();
       // values.put(LogsDb.KEY_TIMESTAMP, 333);
        values.put(LogsDb.KEY_THREAD, "h");
        values.put(LogsDb.KEY_CLASSNAME, "j");
        values.put(LogsDb.KEY_FUNCTION, "k");
        values.put(LogsDb.KEY_LOGLEVEL, "j");
        values.put(LogsDb.KEY_LOGW, "k");
        getContentResolver().insert(LoggerContentProvider.CONTENT_URI, values);
        Log.d("Added", LogsDb.KEY_ROWID);
        finish();


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
