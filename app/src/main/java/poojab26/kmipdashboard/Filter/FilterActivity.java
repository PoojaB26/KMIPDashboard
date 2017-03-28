package poojab26.kmipdashboard.Filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 28-Mar-17.
 */

public class FilterActivity extends Activity {

    Button filterDate, filterLogLevel, filterButton, changeDatebtn;
    CheckBox filterResponse;
    RadioGroup radioLoglevel;
    private RadioButton radioButtonLog;
    LinearLayout dateView;


    DatePicker picker;
    String MY_PREFS_NAME = "DatePicker";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        picker=(DatePicker)findViewById(R.id.datePicker1);

        filterDate = (Button)findViewById(R.id.filterDate);
        filterLogLevel = (Button)findViewById(R.id.filterLogLevel);
        filterResponse = (CheckBox)findViewById(R.id.filterResponse);
        filterButton = (Button)findViewById(R.id.filterbtn);
        changeDatebtn = (Button)findViewById(R.id.changeDatebtn);
        dateView = (LinearLayout) findViewById(R.id.dateView);
        radioLoglevel=(RadioGroup)findViewById(R.id.radioGroupLog);

        filterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateView.setVisibility(View.VISIBLE);

            }
        });

        changeDatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterByDate = new Intent(getBaseContext(),FilterActivityDate.class);
                filterByDate.putExtra("DATE", getCurrentDate());
                startActivity(filterByDate);
            }
        });

        filterLogLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioLoglevel.setVisibility(View.VISIBLE);
                      }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = getSelectedID();


                Intent filterLogLevel= new Intent(getBaseContext(),FilterActivityLogLevel.class);
                filterLogLevel.putExtra("LEVEL", Integer.toString(index));
                startActivity(filterLogLevel);
            }
        });






    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        String month = "";
        switch(picker.getMonth() + 1){
            case 1: month = "Jan";
                break;
            case 2: month = "Feb";
                break;
            case 3: month = "Mar";
                break;
            case 4: month = "Apr";
                break;
            case 5: month = "May";
                break;
            case 6: month = "Jun";
                break;
            case 7: month = "Jul";
                break;
            case 8: month = "Aug";
                break;
            case 9: month = "Sep";
                break;
            case 10: month = "Oct";
                break;
            case 11: month = "Nov";
                break;
            case 12: month = "Dec";
                break;
        }
//        builder.append("Current Date: ");
        builder.append(month+" ");
        builder.append(picker.getDayOfMonth()+", ");
        builder.append(picker.getYear());

      /*  SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("Date", builder.toString());
        editor.commit();*/

        return builder.toString();
    }

    private int getSelectedID() {
        int selectedId=radioLoglevel.getCheckedRadioButtonId();
        radioButtonLog=(RadioButton)findViewById(selectedId);
        int index = radioLoglevel.indexOfChild(radioButtonLog);
        Log.d("RADIO", Integer.toString(index));

        return index;
    }
}
