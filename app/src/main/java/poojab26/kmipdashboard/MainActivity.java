package poojab26.kmipdashboard;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import poojab26.kmipdashboard.Database.LoggerContentProvider;
import poojab26.kmipdashboard.Database.LogsDb;
import poojab26.kmipdashboard.Model.LogModel;

public class MainActivity extends AppCompatActivity {
    String FullMatch = "";
    String Date = "";
    String Time = "";
    String Thread = "";
    String className = "";
    String functionName = "";
    String logLevel = "";
    String LogW = "";
    String stringFromFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String testString = readFromFile(getApplicationContext());
        findMatch(testString);
        //stringToObjects();
    }


    private String readFromFile(Context context) {
    Log.d("HERE", "i am here");

        try {
            InputStream inputStream = getAssets().open("config.txt");
            Reader reader = new InputStreamReader(inputStream, "UTF-8");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString + "\n");
                }

                inputStream.close();
                stringFromFile = stringBuilder.toString();
                Log.d("HELLO", stringFromFile);

                System.out.println(stringFromFile);
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return stringFromFile;
    }
    private void jsonSTuff(){

        String json = "{\n" +
                "    \"timestamp\": 1481626936000,\n" +
                "    \"classname\": \"com.ibm.tklm.server.db.dao.jdbc.Utils\",\n" +
                "    \"function_name\": \"createPreparedStatement(sql)\",\n" +
                "    \"log_level\": \"FINER\",\n" +
                "    \"log\": \"ENTRY INSERT INTO KMT_KMIP_ATTR_CRYPTOPARAMS(BLOCK_CIPHER_MODE,PADDING_METHOD,HASHING_ALGORITHM,ROLE_TYPE,MANAGED_OBJECT_UUID,INDEX_ID,RANDOM_IV,CRYPTOGRAPHIC_ALGORITHM) VALUES (?,?,?,?,?,?,?,?)\"\n" +
                "  }";

        try {

            JSONObject obj = new JSONObject(json);

            Log.d("My App", obj.toString());


        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
        }
        finally {
            Log.d("finally", "here");
        }


    }
    private void findMatch(String myString) {

        String test;

        // Pattern to find code
        String pattern = "([A-Za-z]{3} \\d+, \\d*)( .{8} (AM|PM)) (Thread\\[.*\\]) (com.[a-zA-Z.]+([a-zA-Z])*)(.*)\\s((?:ALL|FINER|FINEST){0,1}:)(.*)";
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        while (m.find()) {
            test = m.group();
            FullMatch = m.group(0);
            Date = m.group(1);
            Time = m.group(2);
            Thread = m.group(4);
            className = m.group(5);
            functionName = m.group(7);
            logLevel = m.group(8);
            LogW = m.group(9);

            Log.d("TeST", test);

            Log.d("LogW", LogW);
            Log.d("LogLevel", logLevel);
            Log.d("functionName", functionName);
            Log.d("className", className);
            Log.d("Thread", Thread);
            Log.d("Day", Date);
            Log.d("Day", Time);


            ContentValues values = new ContentValues();
            values.put(LogsDb.KEY_DATE, Date);
            values.put(LogsDb.KEY_TIME, Time);
            values.put(LogsDb.KEY_THREAD, Thread);
            values.put(LogsDb.KEY_CLASSNAME, className);
            values.put(LogsDb.KEY_FUNCTION, functionName);
            values.put(LogsDb.KEY_LOGLEVEL, logLevel);
            values.put(LogsDb.KEY_LOGW, LogW);
            getContentResolver().insert(LoggerContentProvider.CONTENT_URI, values);
            Log.d("Added", LogsDb.KEY_ROWID);
            finish();
        }


    }

    public void stringToObjects() {
        LogModel logModel = new LogModel();
        //  logModel.setDay(Date.toString());
        logModel.setClassname(className);
        logModel.setFunctionName(functionName);
        logModel.setLogLevel(logLevel);
        logModel.setLog(LogW);


        Gson gson = new Gson();

        System.out.println(gson.toJson(logModel));
        //jsonString.setText(gson.toJson(logModel));

        String logFormat = "";
        String logString = logModel.getLog();
        String formatPattern = "(Tag: Response Message|Tag:Request Message)";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(formatPattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(logString);
        if (m.find()) {
            //  FullMatch = m.group(0);
            logFormat = m.group(1);


        }
        if (logFormat.equalsIgnoreCase("Tag: Response Message")) {
            System.out.println("ITS A RESPONSE");
            logModel.setLogFormat("Response");
        } else {
            System.out.println("REQUEST");
            logModel.setLogFormat("Request");
        }

        Log.d("logFormat", logFormat);
    }

}
