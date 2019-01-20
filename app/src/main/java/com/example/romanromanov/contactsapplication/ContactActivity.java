package com.example.romanromanov.contactsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private String fullName;

    private TextView firstName;
    private TextView lastName;
    private TextView number;
    private TextView email;
    private TextView address;
    private TextView postcode;


    private Contact contact;
    private int contactID;

    public static final String EXTRA_CONTACTID = "contact_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ContactsData contactsData = new ContactsData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get contact id from the intent
        contactID = (Integer)getIntent().getExtras().get(EXTRA_CONTACTID);

        contact = contactsData.getContact(contactID);

        firstName = findViewById(R.id.firstName);
        firstName.setText(contact.getFirstName());

        lastName = findViewById(R.id.lastName);
        lastName.setText(contact.getLastName());

        number = findViewById(R.id.number);
        number.setText(contact.getNumber());

        email = findViewById(R.id.email);
        email.setText(contact.getEmail());

        address =  findViewById(R.id.address);
        address.setText(contact.getAddress());

        postcode = findViewById(R.id.postcode);
        postcode.setText(contact.getPostcode());

        fullName = firstName.getText().toString() + " " + lastName.getText().toString();

    }

    public void onClickCall(View view) {
        Intent intent = new Intent(ContactActivity.this, CallActivity.class);
        intent.putExtra("number", fullName);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit_contact) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickUpdateContact(MenuItem menuItem) {
        Intent intent = new Intent(ContactActivity.this, EditContactActivity.class);
        intent.putExtra(EXTRA_CONTACTID, contactID);
        startActivity(intent);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


}
