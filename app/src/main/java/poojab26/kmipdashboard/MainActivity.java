package poojab26.kmipdashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import poojab26.kmipdashboard.Model.LogModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findMatch("Dec 13, 2016 11:02:16 AM Thread[pool-6-thread-9,5,main] com.ibm.tklm.server.db.dao.jdbc.Insert executeWithColumnPositions(String, String, NamedParameters) ALL: SQL=INSERT INTO KMT_KMIP_ATTR_CRYPTOPARAMS(BLOCK_CIPHER_MODE,PADDING_METHOD,HASHING_ALGORITHM,ROLE_TYPE,MANAGED_OBJECT_UUID,INDEX_ID,RANDOM_IV,CRYPTOGRAPHIC_ALGORITHM) VALUES (?,?,?,?,?,?,?,?)");
       // stringToObjects();
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

        String FullMatch = "";
        String Date = "";
        String Thread = "";
        String Class = "";
        String functionName = "";
        String logLevel = "";
        String LogW = "";



        // Pattern to find code
        String pattern = "([A-Za-z]{3} [1-31]*, \\d{4} \\d{2}:\\d{2}:\\d{2} AM|PM) (Thread\\[.*\\]) (com.[a-zA-Z.]+([a-zA-Z])*)(.*)\\s((?:ALL|FINER|FINEST)):(.*)";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        if (m.find()) {
            FullMatch = m.group(0);
            Date = m.group(1);
            Thread = m.group(2);
            Class = m.group(3);
            functionName = m.group(5);
            logLevel = m.group(6);
            LogW = m.group(7);



        }
        Log.d("MATCH", LogW);
        Log.d("MATCH", logLevel);
        Log.d("MATCH", functionName);
        Log.d("MATCH", Class);
        Log.d("MATCH", Thread);
        Log.d("MATCH", Date);

    }

    public void stringToObjects(){
        LogModel logModel = new LogModel();
        logModel.setTimestamp(146000);
        logModel.setClassname("com.ibm.tklm.server.db.dao.jdbc.Utils");
        logModel.setFunctionName("createPreparedStatement(sql)");
        logModel.setLogLevel("FINER");
        logModel.setLog("ENTRY INSERT INTO KMT_KMIP_ATTR_CRYPTOPARAMS(BLOCK_CIPHER_MODE,PADDING_METHOD,HASHING_ALGORITHM,ROLE_TYPE,MANAGED_OBJECT_UUID,INDEX_ID,RANDOM_IV,CRYPTOGRAPHIC_ALGORITHM) VALUES (?,?,?,?,?,?,?,?)");


        Gson gson = new Gson();

        System.out.println(gson.toJson(logModel));
    }
}
