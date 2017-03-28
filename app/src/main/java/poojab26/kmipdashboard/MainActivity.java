package poojab26.kmipdashboard;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import poojab26.kmipdashboard.Database.LoggerContentProvider;
import poojab26.kmipdashboard.Database.LogsDb;

public class MainActivity extends AppCompatActivity {
    String FullMatch = "";
    String Date = "";
    String Time = "";
    String Thread = "";
    String className = "";
    String functionName = "";
    String logLevel = "";
    String LogW = "";
    String LogResponseMessage = "";

    String stringFromFile = "";
    ArrayList<Integer> TokenIndex = new ArrayList<Integer>();
    ArrayList<Integer> ResponseIndex = new ArrayList<Integer>();

 //   int[] index = new int[100];
    static int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String testString = readFromFile(getApplicationContext());
        findMatch(testString);
        //stringToObjects();
        tokenize(testString);
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
    private void findMatch(String myString) {

        String test;

        // Pattern to find code
        String pattern = "([A-Za-z]{3} \\d+, \\d* .{8})";
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        while (m.find()) {

            while (m.find()) {
                TokenIndex.add(m.start());
                System.out.print("Start index: " + m.start());
                System.out.print(" End index: " + m.end());
                System.out.println(" Found: " + m.group());
            }


        }


    }

    void tokenize(String myString){
        for(int i=0; i<TokenIndex.size()-1; i++){
            String testString = myString.substring(TokenIndex.get(i), TokenIndex.get(i+1));

            Log.d("testString", testString);

            String test;

            // Pattern to find code
            String pattern = "([A-Za-z]{3} \\d+, \\d*)( .{8} (AM|PM)) (Thread\\[.*\\]) (com.[a-zA-Z.]+([a-zA-Z])*)(.*)\\s((?:ALL|FINER|FINEST){0,1}:)(.*)(([\\n]*.*)*)";
            Pattern regEx = Pattern.compile(pattern);

            // Find instance of pattern matches
            Matcher m = regEx.matcher(testString);
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
                LogResponseMessage = m.group(10);

                Log.d("TeST", test);
                Log.d("LogResponse", LogResponseMessage);
                Log.d("LogW", LogW);
                Log.d("LogLevel", logLevel);
                Log.d("functionName", functionName);
                Log.d("className", className);
                Log.d("Thread", Thread);
                Log.d("Day", Date);
                Log.d("Day", Time);
                checkIfResponse(LogW + LogResponseMessage);

                ContentValues values = new ContentValues();
                values.put(LogsDb.KEY_DATE, Date);
                values.put(LogsDb.KEY_TIME, Time);
                values.put(LogsDb.KEY_THREAD, Thread);
                values.put(LogsDb.KEY_CLASSNAME, className);
                values.put(LogsDb.KEY_FUNCTION, functionName);
                values.put(LogsDb.KEY_LOGLEVEL, logLevel);
                values.put(LogsDb.KEY_LOGW, LogW);
                values.put(LogsDb.KEY_OPERATION, LogResponseMessage);
                values.put(LogsDb.KEY_RESULT, LogResponseMessage);
                getContentResolver().insert(LoggerContentProvider.CONTENT_URI, values);
                Log.d("Added", LogsDb.KEY_ROWID);
                finish();
            }





        }
    }

    private void checkIfResponse(String message) {
        String pattern = "(\\bResponse Message*\\b)";
        Pattern regEx = Pattern.compile(pattern);
        String test, response;
        // Find instance of pattern matches
        Matcher m = regEx.matcher(message);
          while (m.find()) {
            test = m.group();
            FullMatch = m.group(0);
            response = m.group(1);
            ResponseIndex.add(m.start());
            Log.d("response", response);
        }

    }
}
