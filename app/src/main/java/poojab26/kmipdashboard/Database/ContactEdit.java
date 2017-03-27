package poojab26.kmipdashboard.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import poojab26.kmipdashboard.R;

/**
 * Created by pblead26 on 26-Mar-17.
 */

public class ContactEdit extends Activity implements View.OnClickListener {

    private Spinner categoryList;
    private Button save, delete;
    private String mode;
    private EditText phone, name, email;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail_page);

        // get the values passed to the activity from the calling activity
        // determine the mode - add, update or delete
        if (this.getIntent().getExtras() != null){
            Bundle bundle = this.getIntent().getExtras();
            mode = bundle.getString("mode");
        }

        // get references to the buttons and attach listeners
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        phone = (EditText) findViewById(R.id.phone);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);



        // create a dropdown for users to select various continents
        categoryList = (Spinner) findViewById(R.id.categoryList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryList.setAdapter(adapter);

        // if in add mode disable the delete option
        if(mode.trim().equalsIgnoreCase("add")){
            delete.setEnabled(false);
        }
        // get the rowId for the specific country
        else{
            Bundle bundle = this.getIntent().getExtras();
            id = bundle.getString("rowId");
            loadContactInfo();
        }

    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.save:
                ContentValues values = new ContentValues();
                values.put(ContactsDb.KEY_PHONE, 645);
                values.put(ContactsDb.KEY_NAME, "h");
                values.put(ContactsDb.KEY_CATEGORY, "j");
                values.put(ContactsDb.KEY_EMAIL, "k");
getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                finish();
                break;

            case R.id.delete:
                // delete a record
                Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id);
                getContentResolver().delete(uri, null, null);
                finish();
                break;

            // More buttons go here (if any) ...

        }
    }

    // based on the rowId get all information from the Content Provider
    // about that country
    private void loadContactInfo(){

        String[] projection = {
                ContactsDb.KEY_ROWID,
                ContactsDb.KEY_PHONE,
                ContactsDb.KEY_NAME,
                ContactsDb.KEY_EMAIL,
                ContactsDb.KEY_CATEGORY

        };
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id);
        Cursor cursor = getContentResolver().query(uri, projection, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            String myPhone = cursor.getString(cursor.getColumnIndexOrThrow(ContactsDb.KEY_PHONE));
            String myName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsDb.KEY_NAME));
            String myCategory = cursor.getString(cursor.getColumnIndexOrThrow(ContactsDb.KEY_CATEGORY));
            String myEmail = cursor.getString(cursor.getColumnIndexOrThrow(ContactsDb.KEY_EMAIL));

            phone.setText(myPhone);
            name.setText(myName);
            email.setText(myEmail);

            categoryList.setSelection(getIndex(categoryList, myCategory));
        }


    }

    // this sets the spinner selection based on the value
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

}
