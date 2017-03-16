package com.example.bruger.examapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactEdit extends AppCompatActivity {


    EditText eName, ePhone, eEmail, eAddress, eUrl;
    Button save;
    Contact c;
    private State state;
    public enum State{
        edit,
        add
    }
    int id;
    ContactAutoGen cag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        cag = ContactAutoGen.getInstance();


        c = (Contact)getIntent().getExtras().getSerializable("Contact");
        if (c == null)
        {
            state= State.add;
            c = new Contact(0,"","","","","","");
            id = 0;

        }
        else
        {
            state= State.edit;
            id= c.getId();
        }
        init();

    }

    private void init() {

        save = (Button) findViewById(R.id.btnSave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(id ==0)
                {
                    id = cag.getId();
                }
                c = new Contact(id ,eUrl.getText().toString(),"",eEmail.getText().toString(),eAddress.getText().toString()
                        ,eName.getText().toString(),ePhone.getText().toString());


                if (state == State.add)
                {
                    cag.AddContact(c);
                    Intent i = new Intent(ContactEdit.this, MainActivity.class);
                    setResult(RESULT_OK, i);

                    finish();
                }
                else {
                    cag.editContact(c);
                    Intent i = new Intent(ContactEdit.this, contact_details.class);
                    i.putExtra("con",c);
                    setResult(RESULT_OK, i);

                    finish();

                }
            }
        });


        eName = (EditText) findViewById(R.id.txteName);

        ePhone = (EditText) findViewById(R.id.txtePhone);

        eEmail = (EditText) findViewById(R.id.txteEmail);

        eAddress = (EditText) findViewById(R.id.txteAddress);

        eUrl = (EditText) findViewById(R.id.txteURL);

        eName.setText(c.getName());
        eAddress.setText(c.getAddress());
        eEmail.setText(c.getEmail());
        ePhone.setText(c.getPhoneNr());
        eUrl.setText(c.getUrl());


    }
}
