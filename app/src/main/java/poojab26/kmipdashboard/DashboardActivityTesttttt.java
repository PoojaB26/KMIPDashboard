package poojab26.kmipdashboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by pblead26 on 23-Mar-17.
 */

public class DashboardActivityTesttttt extends Activity {
    TableRow mainRow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dashboard);
        TableLayout table = (TableLayout) findViewById(R.id.myTableLayout);
        for (int i = 0; i < 5; i++) {

            TableRow tableRow2 = new TableRow(DashboardActivityTesttttt.this);


           /* TextView tvid=(TextView)findViewById(R.id.one);
            tvid.setText(i + " NUMBER " + "ONE");
            TextView tvid2=(TextView)findViewById(R.id.two);
            tvid2.setText(i + " NUMBER " + "TWO");
            Log.d("now", i+" num");
            tableRow2.addView(tvid);
            tableRow2.addView(tvid2);

            table.addView(tableRow2);*/

            TextView tv1=new TextView(this);
            tv1.setText("ONE"+i);
            TextView tv2=new TextView(this);
            tv2.setText("TWO"+i);
            tableRow2.addView(tv1);
            tableRow2.addView(tv2);
            table.addView(tableRow2);

        }
    }
}
