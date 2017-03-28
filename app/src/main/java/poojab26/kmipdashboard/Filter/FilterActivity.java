package poojab26.kmipdashboard.Filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 28-Mar-17.
 */

public class FilterActivity extends Activity {

    Button filterDate, filterLogLevel, filterResponse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterDate = (Button)findViewById(R.id.filterDate);
        filterLogLevel = (Button)findViewById(R.id.filterLogLevel);
        filterResponse = (Button)findViewById(R.id.filterResponse);

        filterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterLogDate= new Intent(getBaseContext(),Activity_Date.class);
                startActivity(filterLogDate);
            }
        });



    }
}
