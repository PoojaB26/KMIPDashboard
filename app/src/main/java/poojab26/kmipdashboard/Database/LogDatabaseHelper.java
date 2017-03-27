package poojab26.kmipdashboard.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pblead26 on 26-Mar-17.
 */

public class LogDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Logs";
    private static final int DATABASE_VERSION = 1;

    LogDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LogsDb.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogsDb.onUpgrade(db, oldVersion, newVersion);
    }
}