package com.example.bruger.examapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    ListView lstview;
    ContactListViewAdapter clva;
    ContactAutoGen cag;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        clva.contacts = cag.getContacts();
        clva.notifyDataSetChanged();

       /* if(requestCode == 1)//add
        {
            switch (requestCode)
            {
                case RESULT_OK:
                        break;
                case RESULT_CANCELED:
                    break;
            }

        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Contact c = null;
        if(id == R.id.action_add)
        { Intent i = new Intent(this, ContactEdit.class);
            i.putExtra("Contact", c);
            startActivityForResult(i, 1);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cag = ContactAutoGen.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle("Contacts");

        contacts = cag.makeContacts();
        clva = new ContactListViewAdapter(this, R.layout.contactrowcell, contacts);

        lstview = (ListView) findViewById(R.id.LstContact);

        lstview.setAdapter(clva);


        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactListClick(i);
            }
        });


    }

    private void ContactListClick(int position){
       Contact c = contacts.get(position);

        Intent i = new Intent(this, contact_details.class);
        i.putExtra("Contact", c);
        startActivityForResult(i, 1);
    }

}
