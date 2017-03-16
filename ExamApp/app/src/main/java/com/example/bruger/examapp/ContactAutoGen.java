package com.example.bruger.examapp;

import java.util.ArrayList;

/**
 * Created by Bruger on 07-03-2017.
 */

public class ContactAutoGen {

    private static ArrayList<Contact> con;
    private static ContactAutoGen instance = null;
    private static int  id = 1;

    private ContactAutoGen() {
    }

    public static ContactAutoGen getInstance(){
        if (instance == null)
        {
            instance = new ContactAutoGen();
        }
        return instance;
    }


    public ArrayList<Contact> makeContacts(){
        if(con == null)
        {
            con = new ArrayList<>();
        }
        if(con.size() == 0)
        {
            for (int i = 0; i<5; i++)
            {
                Contact c = new Contact(getId(),"www.somesite.org", "6710", "ag@dk.com","nørregade 16","Anders "+i,"20861504");
                con.add(c);
                c = new Contact(getId(),"www.somesite.dk", "6700", "pa@dk.com","skolegade 1","Patrick "+i,"15152018");
                con.add(c);
                c = new Contact(getId(),"www.somesitegame.dk", "8210", "pa@dk.com","skolegade 1","Mor "+i,"15152018");
                con.add(c);
                c = new Contact(getId(),"www.somesitenot.dk", "4800", "esb@dk.com","skolegade 63","Esben "+i,"20564299");
                con.add(c);
                c = new Contact(getId(),"www.somesitewho.dk", "6200", "ed@dk.com","skolegade 63","Emil "+i,"65668631");
                con.add(c);
            }
        }
        return con;
    }

    public ArrayList<Contact> getContacts(){
        return con;
    }


    public ArrayList<Contact> AddContact(Contact contact){
        con.add(contact);
        return  con;
    }


public int getId(){
    return id++;
}

    public Contact editContact(Contact c) {
        for (int i = 0; i<con.size(); i++) {

            Contact cc = con.get(i);
            if (cc.getId() == c.getId())
            {
                con.remove(i);
                con.add(i,c);
                return con.get(i);
            }
        }
        return null;
    }
}
