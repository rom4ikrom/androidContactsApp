package com.example.romanromanov.contactsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class EditContactActivity extends AppCompatActivity {

    MenuItem updateMenuItem;

    EditText firstName;
    EditText lastName;
    EditText number;
    EditText email;
    EditText address;
    EditText postcode;

    int contactID;
    Contact contact;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        postcode= findViewById(R.id.postcode);

        //get the contact id from Contact Activity
        contactID = (Integer) getIntent().getExtras().get(ContactActivity.EXTRA_CONTACTID);

        //get the contact's information based on the given contact id
        ContactsData contactsData = new ContactsData();
        contact = contactsData.getContact(contactID);

        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());
        number.setText(contact.getNumber());
        email.setText(contact.getEmail());
        address.setText(contact.getAddress());
        postcode.setText(contact.getPostcode());

        //add text changed listener to first name text edit field
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0 && number.getText().toString().length() == 11 && lastName.getText().toString().length() > 0) {
                    updateMenuItem.setEnabled(true);
                    updateMenuItem.setVisible(true);
                } else {
                    updateMenuItem.setEnabled(false);
                    updateMenuItem.setVisible(false);
                }
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0 && number.getText().toString().length() == 11 && firstName.getText().toString().length() > 0) {
                    updateMenuItem.setEnabled(true);
                    updateMenuItem.setVisible(true);
                } else {
                    updateMenuItem.setEnabled(false);
                    updateMenuItem.setVisible(false);
                }
            }
        });

        //add text changed listener to number edit field
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 11 && firstName.getText().toString().length() > 0 && lastName.getText().toString().length() > 0) {
                    updateMenuItem.setEnabled(true);
                    updateMenuItem.setVisible(true);
                } else {
                    updateMenuItem.setEnabled(false);
                    updateMenuItem.setVisible(false);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);

        updateMenuItem = menu.findItem(R.id.action_update_contact);

        if(number.getText().toString().length() > 0 && firstName.getText().length() > 0 && lastName.getText().toString().length() > 0 ) {
            updateMenuItem.setEnabled(true);
            updateMenuItem.setVisible(true);
        } else {
            updateMenuItem.setEnabled(false);
            updateMenuItem.setVisible(false);
        }



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_contact) {
            return true;
        }

        if (id == R.id.action_delete_contact) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickUpdateContact(MenuItem menuItem) {

        //updates the contact information in Realm
        ContactListApplication.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                contact.setFirstName(firstName.getText().toString().trim());
                contact.setLastName(lastName.getText().toString().trim());
                contact.setNumber(number.getText().toString().trim());
                contact.setEmail(email.getText().toString().trim());
                contact.setAddress(address.getText().toString().trim());
                contact.setPostcode(postcode.getText().toString().trim());
            }
        });

        ContactsData contactsData =  new ContactsData();

        int index = contactsData.getContactIndex(contact);

        Intent intent = new Intent(EditContactActivity.this, ContactActivity.class);
        intent.putExtra(ContactActivity.EXTRA_CONTACTID, index);
        startActivity(intent);
    }

    //remove the contact from realm database and open the Main Activity
    public void onClickDeleteContact(MenuItem menuItem) {
        ContactsData contactsData = new ContactsData();
        contactsData.removeContact(contact);
        Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
