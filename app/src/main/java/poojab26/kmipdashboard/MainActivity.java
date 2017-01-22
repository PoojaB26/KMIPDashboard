package poojab26.kmipdashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findMatch("34267585a");
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
}
