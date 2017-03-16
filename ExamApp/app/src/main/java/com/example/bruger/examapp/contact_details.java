package com.example.bruger.examapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class contact_details extends AppCompatActivity {

    TextView phone, mail, adres, site;
    Button picture, call, sms;
    ImageView imgview;
    Contact c = new Contact(0, "No Contact", "", "", "", "", "");

    private final static int editRequstcode = 2;
    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    File m_file;

    // return methods setup
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == editRequstcode) {

            c = (Contact) data.getExtras().getSerializable("con");

            phone.setText(c.getPhoneNr());
            mail.setText(c.getEmail());
            site.setText(c.getUrl());
            adres.setText(c.getAddress());
            getSupportActionBar().setTitle(c.getName());
        }

        else if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String filename = m_file.toString();
                c.setImagePath(filename);
                Log.d("img",c.getImagePath());
                imgview.setImageURI(Uri.fromFile(new File(c.getImagePath())));

                // showPictureTaken(filename);

            } else if (resultCode == RESULT_CANCELED) {
                return;
            }
        }
    }


    // toolbar setup
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    // toolbar button setup
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent i = new Intent(this, ContactEdit.class);
            i.putExtra("Contact", c);
            startActivityForResult(i, editRequstcode);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        c = (Contact) getIntent().getExtras().getSerializable("Contact");
        getSupportActionBar().setTitle(c.getName());

        //sms setup
        sms = (Button) findViewById(R.id.btnSms);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

        //call setup
        call = (Button) findViewById(R.id.btnCall);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall();
            }
        });

        // photo setup
        picture = (Button) findViewById(R.id.btnImg);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        //textfields setup
        phone = (TextView) findViewById(R.id.txtPhone);
        mail = (TextView) findViewById(R.id.txtMail);
        adres = (TextView) findViewById(R.id.txtAddress);
        site = (TextView) findViewById(R.id.txtSite);
        imgview = (ImageView) findViewById(R.id.imageView);

        if(c.getImagePath() != null && !c.getImagePath().isEmpty())
        {
            imgview.setImageURI(Uri.fromFile(new File(c.getImagePath())));
        }

        phone.setText(c.getPhoneNr());
        mail.setText(c.getEmail());
        site.setText(c.getUrl());
        adres.setText(c.getAddress());


    }

    private void sendSMS() {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("smsto:" + c.getPhoneNr()));

        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }

    }

    private void makeCall() {
        Intent callintent = new Intent(Intent.ACTION_DIAL);
        callintent.setData(Uri.parse("tel:" + c.getPhoneNr()));

        if (callintent.resolveActivity(getPackageManager()) != null) {
            startActivity(callintent);
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        m_file = getImageFile(); // create a file to save the image
         intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(m_file)); // set the image file name

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        // start the image capture Intent

    }

    private File getImageFile() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES),"");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {

                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile = new File(mediaStorageDir.getPath() +
                File.separator + "IMG" +
                "_" + timeStamp + "." + "jpeg");

        return mediaFile;
    }

}
