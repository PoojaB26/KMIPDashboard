package poojab26.kmipdashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonString = (TextView)findViewById(R.id.jsonString);
        findMatch(" Dec 15, 2016 12:28:20 PM Thread[pool-6-thread-3,5,main] com.ibm.tklm.kmip.stub.SSLRequestProcessor run ALL: Response message: KMIPMessage: Tag: Response Message (0x42007B), Type: Structure (0x01), Data: \n" +
                "  Tag: Response Header (0x42007A), Type: Structure (0x01), Data: \n" +
                "    Tag: Protocol Version (0x420069), Type: Structure (0x01), Data: \n" +
                "      Tag: Protocol Version Major (0x42006A), Type: Integer (0x02), Data: 0x00000001 (1)\n" +
                "      Tag: Protocol Version Minor (0x42006B), Type: Integer (0x02), Data: 0x00000002 (2)\n" +
                "    Tag: Time Stamp (0x420092), Type: Date-Time (0x09), Data: 0x0000000058533524 (Thu Dec 15 12:28:20 GMT-12:00 2016)\n" +
                "    Tag: Batch Count (0x42000D), Type: Integer (0x02), Data: 0x00000001 (1)\n" +
                "  Tag: Batch Item (0x42000F), Type: Structure (0x01), Data: \n" +
                "    Tag: Operation (0x42005C), Type: Enumeration (0x05), Data: 0x00000014 (Destroy)\n" +
                "    Tag: Result Status (0x42007F), Type: Enumeration (0x05), Data: 0x00000000 (Success)\n" +
                "    Tag: Response Payload (0x42007C), Type: Structure (0x01), Data: \n" +
                "      Tag: Unique Identifier (0x420094), Type: Text String (0x07), Data: KEY-ddd2652-382a0081-613e-4758-a9a5-44fdc1212f2f\n");
        stringToObjects();
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





        // Pattern to find code
        String pattern = "([A-Za-z]{3} [1-31]*, \\d{4} \\d{2}:\\d{2}:\\d{2} AM|PM) (Thread\\[.*\\]) (com.[a-zA-Z.]+([a-zA-Z])*)(.*)\\s((?:ALL|FINER|FINEST)):(.*)";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        if (m.find()) {
            FullMatch = m.group(0);
            Date = m.group(1);
            Thread = m.group(2);
            className = m.group(3);
            functionName = m.group(5);
            logLevel = m.group(6);
            LogW = m.group(7);



        }
        Log.d("LogW", LogW);
        Log.d("LogLevel", logLevel);
        Log.d("functionName", functionName);
        Log.d("className", className);
        Log.d("Thread", Thread);
        Log.d("Date", Date);

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
