package com.example.bruger.examapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bruger on 07-03-2017.
 */

public class ContactListViewAdapter extends ArrayAdapter{

    Context con;
    int res;
    ArrayList<Contact> contacts = null;
    public ContactListViewAdapter(Context context, int resource, ArrayList<Contact> contacts) {
        super(context, resource, contacts);
        this.con = context;
        this.res = resource;
        this.contacts = contacts;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact c = contacts.get(position);

        if (convertView ==null)
        {
            convertView = LayoutInflater.from(con).inflate(R.layout.contactrowcell, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.txtName);

        name.setText(c.getName());

        if (position%2 ==1)
        {
            convertView.setBackgroundColor(Color.LTGRAY);
        }
        else
        {
            convertView.setBackgroundColor(Color.WHITE);
        }



        return convertView;
    }
}
