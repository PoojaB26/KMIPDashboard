package poojab26.kmipdashboard.Database;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import poojab26.kmipdashboard.R;


/**
 * Created by pblead26 on 26-Mar-17.
 */

public class MainActivity extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor>{
        private static final String AUTHORITY = "com.pblead26.contentprovider";

        // create content URIs from the authority by appending path to database table
        public static final Uri URL =
                Uri.parse("content://" + AUTHORITY + "/logs");
        private SimpleCursorAdapter dataAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                displayListView();

                Button add = (Button) findViewById(R.id.add);
                add.setOnClickListener(new OnClickListener() {

                        public void onClick(View v) {
                                // starts a new Intent to add a Country
                                Intent contactEdit = new Intent(getBaseContext(), LogEdit.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("mode", "add");
                                contactEdit.putExtras(bundle);
                                startActivity(contactEdit);
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
                String[] columns = new String[] {
                        LogsDb.KEY_TIMESTAMP,
                        LogsDb.KEY_THREAD,
                        LogsDb.KEY_CLASSNAME,
                        LogsDb.KEY_FUNCTION,
                        LogsDb.KEY_LOGLEVEL,
                        LogsDb.KEY_LOGW
                };

                // the XML defined views which the data will be bound to
                int[] to = new int[] {
                        R.id.tvTimestamp,
                        R.id.tvThread,
                        R.id.tvClassname,
                        R.id.tvFunction,
                        R.id.tvLoglevel,
                        R.id.tvLogw,

                };

                // create an adapter from the SimpleCursorAdapter
                dataAdapter = new SimpleCursorAdapter(
                        this,
                        R.layout.contact_info,
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
                        LogsDb.KEY_TIMESTAMP,
                        LogsDb.KEY_THREAD,
                        LogsDb.KEY_CLASSNAME,
                        LogsDb.KEY_FUNCTION,
                        LogsDb.KEY_LOGLEVEL,
                        LogsDb.KEY_LOGW};
                CursorLoader cursorLoader = new CursorLoader(this,
                        LoggerContentProvider.CONTENT_URI, projection, null, null, null);
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
