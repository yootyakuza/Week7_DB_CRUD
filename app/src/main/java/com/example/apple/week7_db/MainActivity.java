package com.example.apple.week7_db;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sarayut on 28/2/2018 AD.
 */

public class MainActivity extends AppCompatActivity {
    EditText etName, etPhone, etSalary;
    Button butAdd,butEdit,butDel;
    private Context context;
    private boolean Formatting;
    private int After;
    private DatabaseHandler db;
    private List<Contact> contacts = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editTextAddName);
        etName.requestFocus();
        etPhone = (EditText) findViewById(R.id.editTextAddPhone);
        etSalary = (EditText) findViewById(R.id.editTextAddSarary);
        butAdd = (Button) findViewById(R.id.butAdd);
        butEdit = (Button)findViewById(R.id.butEdit);
        butDel = (Button)findViewById(R.id.butDel);
        butEdit.setEnabled(false);
        butDel.setEnabled(false);

        context = this;
        db = new DatabaseHandler(this);

        contacts = db.getAllContacts();
        if(contacts.size() > 0){
            listView();
            butEdit.setEnabled(true);
            butDel.setEnabled(true);
        }

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                After = i2; // เช็คการกด backspace
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Formatting) {
                    Formatting = true;
                    if(After != 0) //กรณีที่ไม่ได้กด backspace ใช้ format US และกำหนด length ถ้าไทยจะเป็น getDefault() แต่จะไม่ได้ผลลัพธ์ที่ต้องการคือ xxx-xxx-xxxx
                        PhoneNumberUtils.formatNumber(editable, PhoneNumberUtils.getFormatTypeForLocale(Locale.US));
                    Formatting = false;
                }
            }
        });

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().isEmpty()) {
                    Toast t = Toast.makeText(getApplication(), "Please input New Column name !!", Toast.LENGTH_SHORT);
                    t.show();
                    etName.requestFocus();

                } else if (etPhone.getText().toString().isEmpty()) {
                    Toast t = Toast.makeText(getApplication(), "Please input Phone number !!", Toast.LENGTH_SHORT);
                    t.show();
                    etPhone.requestFocus();

                } else if (etSalary.getText().toString().isEmpty()) {
                    Toast t = Toast.makeText(getApplication(), "Please input Salary !!", Toast.LENGTH_SHORT);
                    t.show();
                    etSalary.requestFocus();

                } else {
                    db.addContact(new Contact(etName.getText().toString(), etPhone.getText().toString(), etSalary.getText().toString()));
                    contacts = db.getAllContacts();
                    if (contacts.size() > 0) {
                        editTextClear();
                    }
                    listView();
                    butEdit.setEnabled(true);
                    butDel.setEnabled(true);
                }
            }
        });
        butEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Activity2Edit.class);
                startActivity(intent);
            }
        });
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Activity3Del.class);
                startActivity(intent);
            }
        });
    }

    public void editTextClear() {
        etName.setText("");
        etPhone.setText("");
        etSalary.setText("");
        etName.requestFocus();
    }
    public void listView(){
        String[] datas = new String[contacts.size()];

        String[] datas1 = new String[contacts.size()];

        String[] datas2 = new String[contacts.size()];

        for (int i = 0; i < datas.length; i++) {
            datas[i] = contacts.get(i)._name;
            datas1[i] = contacts.get(i)._phone_number;
            datas2[i] = contacts.get(i)._salary;
        }

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), datas, datas1, datas2);
        ListView listView = (ListView) findViewById(R.id.ListView1);
        listView.setAdapter(adapter);
    }
}
