package poojab26.kmipdashboard.Filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 28-Mar-17.
 */

public class Activity_Date extends Activity {
    DatePicker picker;
    Button displayDate;
    TextView textview1;
    String MY_PREFS_NAME = "DatePicker";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        textview1=(TextView)findViewById(R.id.textView1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        displayDate=(Button)findViewById(R.id.button1);

        textview1.setText(getCurrentDate());

        displayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               // textview1.setText(getCurrentDate());

                Intent filterByDate = new Intent(getBaseContext(),FilterActivityDate.class);
                filterByDate.putExtra("DATE", getCurrentDate());
                startActivity(filterByDate);
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
}
