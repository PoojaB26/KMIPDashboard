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

        findMatch("34267585a");
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

        String match1 = "";
        String match2 = "";
        String match3 = "";



        // Pattern to find code
        String pattern = "^([0-9]{8})([a-z])$";  // Sequence of 8 digits
        Pattern regEx = Pattern.compile(pattern);

        // Find instance of pattern matches
        Matcher m = regEx.matcher(myString);
        if (m.find()) {
            match1 = m.group(0);
            match2 = m.group(1);
            match3 = m.group(2);


        }
        Log.d("MATCH", match2);
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
