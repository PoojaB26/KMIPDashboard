package poojab26.kmipdashboard.Database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by pblead26 on 26-Mar-17.
 */

public class LogsDb {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";

    public static final String KEY_THREAD = "thread";
    public static final String KEY_CLASSNAME = "classname";
    public static final String KEY_FUNCTION = "function";
    public static final String KEY_LOGLEVEL = "loglevel";
    public static final String KEY_LOGW = "logw";
    public static final String KEY_OPERATION = "operation";
    public static final String KEY_RESULT = "result";



    private static final String LOG_TAG = "LogsDb";
    public static final String SQLITE_TABLE = "Logger";

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_DATE + "," +
                    KEY_TIME + "," +
                    KEY_THREAD + "," +
                    KEY_CLASSNAME + "," +
                    KEY_FUNCTION + "," +
                    KEY_LOGLEVEL + "," +
                    KEY_LOGW + "," +
                    KEY_OPERATION + "," +
                    KEY_RESULT + "," +

                    " UNIQUE (" + KEY_ROWID +"));";

    public static void onCreate(SQLiteDatabase db) {
        Log.w(LOG_TAG, DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
        onCreate(db);
    }


}
