package poojab26.kmipdashboard.Filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 28-Mar-17.
 */

public class FilterActivity extends Activity {

    Button filterDate, filterLogLevel, filterResponse;
    RadioGroup radioLoglevel;
    private RadioButton radioButtonLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterDate = (Button)findViewById(R.id.filterDate);
        filterLogLevel = (Button)findViewById(R.id.filterLogLevel);
        filterResponse = (Button)findViewById(R.id.filterResponse);
        radioLoglevel=(RadioGroup)findViewById(R.id.radioGroupLog);

        filterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterLogDate= new Intent(getBaseContext(),Activity_Date.class);
                startActivity(filterLogDate);
            }
        });

        filterLogLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId=radioLoglevel.getCheckedRadioButtonId();
                radioButtonLog=(RadioButton)findViewById(selectedId);
                int index = radioLoglevel.indexOfChild(radioButtonLog);
                Log.d("RADIO", Integer.toString(index));

                Intent filterLogLevel= new Intent(getBaseContext(),FilterActivityLogLevel.class);
                filterLogLevel.putExtra("LEVEL", Integer.toString(index));
                startActivity(filterLogLevel);
            }
        });






    }
}
