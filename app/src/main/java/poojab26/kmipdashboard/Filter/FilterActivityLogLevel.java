package poojab26.kmipdashboard.Filter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import poojab26.kmipdashboard.Database.LoggerContentProvider;
import poojab26.kmipdashboard.Database.LogsDb;
import poojab26.kmipdashboard.R;


/**
 * Created by pblead26 on 26-Mar-17.
 */

public class FilterActivityLogLevel extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private static final String AUTHORITY = "com.pblead26.contentprovider";

    // create content URIs from the authority by appending path to database table
    public static final Uri URL =
            Uri.parse("content://" + AUTHORITY + "/logs");
    private SimpleCursorAdapter dataAdapter;
    String MY_PREFS_NAME = "DatePicker";
    String FilteredLevel = "";
    Button filter;
    String[] logLevel ={"ALL:", "FINER:", "FINEST:"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        FilteredLevel = getIntent().getExtras().getString("LEVEL");
        Log.d("picked", FilteredLevel);
        displayListView();
        Intent contactEdit = new Intent(getBaseContext(), poojab26.kmipdashboard.MainActivity.class);
        startActivity(contactEdit);


        filter = (Button) findViewById(R.id.btnFilter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterLog = new Intent(getBaseContext(), FilterActivity.class);
                startActivity(filterLog);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Starts a new or restarts an existing Loader in this manager
        getLoaderManager().restartLoader(0, null, this);
    }

    private void displayListView() {


        // The desired columns to be bound
        String[] columns = new String[]{
                LogsDb.KEY_DATE,
                LogsDb.KEY_TIME,
                LogsDb.KEY_THREAD,
                LogsDb.KEY_CLASSNAME,
                LogsDb.KEY_FUNCTION,
                LogsDb.KEY_LOGLEVEL,
                LogsDb.KEY_LOGW
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.tvDate,
                R.id.tvTime,
                R.id.tvThread,
                R.id.tvClassname,
                R.id.tvFunction,
                R.id.tvLoglevel,
                R.id.tvLogw,

        };

        // create an adapter from the SimpleCursorAdapter
        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.log_structure_display_item,
                null,
                columns,
                to,
                0);

        // get reference to the ListView
        ListView listView = (ListView) findViewById(R.id.contactList);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //Ensures a loader is initialized and active.
        getLoaderManager().initLoader(0, null, this);

    }

    // This is called when a new Loader needs to be created.
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LogsDb.KEY_ROWID,
                LogsDb.KEY_DATE,
                LogsDb.KEY_TIME,
                LogsDb.KEY_THREAD,
                LogsDb.KEY_CLASSNAME,
                LogsDb.KEY_FUNCTION,
                LogsDb.KEY_LOGLEVEL,
                LogsDb.KEY_LOGW};


        String selection = LogsDb.KEY_LOGLEVEL + "=?";
        int index= Integer.parseInt(FilteredLevel);
        String[] selectionArgs = {logLevel[index]};
        CursorLoader cursorLoader = new CursorLoader(this,
                LoggerContentProvider.CONTENT_URI, projection, selection, selectionArgs, "function");
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        dataAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        dataAdapter.swapCursor(null);
    }


}
