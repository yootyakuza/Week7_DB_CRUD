package com.example.apple.week7_db;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity3Del extends AppCompatActivity {
    Button butDelete;
    EditText editDel;
    RelativeLayout relativeLayout;
    private DatabaseHandler db;
    private List<Contact> contacts = new ArrayList<Contact>();
    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity3_del);

        butDelete = (Button)findViewById(R.id.buttonDelete);
        editDel = (EditText)findViewById(R.id.editID);
        relativeLayout = (RelativeLayout)findViewById(R.id.LayoutID);
        db = new DatabaseHandler(this);
        contacts = db.getAllContacts();

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contacts.size() > 0){
                    result = Integer.parseInt(editDel.getText().toString());
                    db.deleteContact(new Contact(result));
                    Snackbar snackbar = Snackbar.make(relativeLayout,"Delete successfully",Snackbar.LENGTH_LONG);
                    snackbar.show();
                    finish();
                }
                else {
                    Toast t = Toast.makeText(getApplicationContext(),contacts.size() + ":Rows",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

    }
}
