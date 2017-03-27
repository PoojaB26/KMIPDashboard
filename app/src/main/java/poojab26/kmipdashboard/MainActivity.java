package poojab26.kmipdashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

import poojab26.kmipdashboard.Model.LogModel;

public class MainActivity extends AppCompatActivity {
    TextView jsonString;
    String FullMatch = "";
    String Date = "";
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
       // jsonString = (TextView)findViewById(R.id.jsonString);
        String testString = readFromFile(getApplicationContext());
//        findMatch("Dec 13, 2016 11:02:16 AM Thread[pool-6-thread-9,5,main] com.ibm.tklm.server.db.dao.jdbc.Utils createPreparedStatement(sql) FINER: ENTRY INSERT INTO KMT_KMIP_ATTR_CRYPTOPARAMS(BLOCK_CIPHER_MODE,PADDING_METHOD,HASHING_ALGORITHM,ROLE_TYPE,MANAGED_OBJECT_UUID,INDEX_ID,RANDOM_IV,CRYPTOGRAPHIC_ALGORITHM) VALUES (?,?,?,?,?,?,?,?) Dec 13, 2016 11:02:16 AM Thread[pool-6-thread-9,5,main] com.ibm.tklm.server.db.dao.jdbc.ConnectionFactory getConnection() FINER: ENTRY");
      /*  findMatch("Dec 13, 2016 11:02:16 AM \n" +
                "Thread[pool-6-thread-9,5,main]\n" +
                "com.ibm.tklm.server.db.dao.jdbc.Utils \n" +
                "createPreparedStatement(sql)\n" +
                "FINER: ENTRY INSERT INTO KMT_KMIP_ATTR_CRYPTOPARAMS(BLOCK_CIPHER_MODE,PADDING_METHOD,HASHING_ALGORITHM,ROLE_TYPE,MANAGED_OBJECT_UUID,INDEX_ID,RANDOM_IV,CRYPTOGRAPHIC_ALGORITHM) VALUES (?,?,?,?,?,?,?,?)\n" +
                "\n" +
                "Dec 13, 2016 11:02:16 AM \n" +
                "Thread[pool-6-thread-9,5,main] \n" +
                "com.ibm.tklm.server.db.dao.jdbc.ConnectionFactory \n" +
                "getConnection()\n" +
                "FINER: ENTRY");
*/
        findMatch(testString);
        stringToObjects();
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



        String test = new String();

        // Pattern to find code
        String pattern = "([A-Za-z]{3} \\d+, \\d* .{8} (AM|PM)) (Thread\\[.*\\]) (com.[a-zA-Z.]+([a-zA-Z])*)(.*)\\s((?:ALL|FINER|FINEST)\n{0,1}:)(.*\n)";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        while (m.find()) {
            test = m.group();
            FullMatch = m.group(0);
            Date = m.group(1);
            Thread = m.group(3);
            className = m.group(4);
            functionName = m.group(6);
            logLevel = m.group(7);
            LogW = m.group(8);

            Log.d("TeST", test);

            Log.d("LogW", LogW);
            Log.d("LogLevel", logLevel);
            Log.d("functionName", functionName);
            Log.d("className", className);
            Log.d("Thread", Thread);
            Log.d("Date", Date);
        }


    }

    public void stringToObjects(){
        LogModel logModel = new LogModel();
        logModel.setTimestamp(Date.toString());
        logModel.setClassname(className);
        logModel.setFunctionName(functionName);
        logModel.setLogLevel(logLevel);
        logModel.setLog(LogW);


        Gson gson = new Gson();

        System.out.println(gson.toJson(logModel));
        jsonString.setText(gson.toJson(logModel));

        String logFormat = "";
        String logString = logModel.getLog();
        String pattern = "(Tag: Response Message|Tag:Request Message)";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(logString);
        if (m.find()) {
          //  FullMatch = m.group(0);
            logFormat = m.group(1);



        }
        if(logFormat.equalsIgnoreCase("Tag: Response Message"))
            System.out.println("ITS A RESPONSE");
        else
            System.out.println("REQUEST");
        Log.d("logFormat", logFormat);
    }


}
