package com.example.romanromanov.contactsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.RealmResults;

public class AddContactActivity extends AppCompatActivity {

    MenuItem saveMenuItem;

    EditText firstName;
    EditText lastName;
    EditText number;
    EditText email;
    EditText address;
    EditText postcode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //enables the back button in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get information from ui
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        postcode= findViewById(R.id.postcode);

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
                if(s.length() > 0 && number.getText().toString().length() == 11 && lastName.getText().toString().length() > 0
                        && postcode.getText().toString().length() >= 6 && postcode.getText().toString().length() <= 8) {
                    saveMenuItem.setEnabled(true);
                    saveMenuItem.setVisible(true);
                } else {
                    saveMenuItem.setEnabled(false);
                    saveMenuItem.setVisible(false);
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
                if(s.length() > 0 && number.getText().toString().length() == 11 && firstName.getText().toString().length() > 0
                        && postcode.getText().toString().length() >= 6 && postcode.getText().toString().length() <= 8) {
                    saveMenuItem.setEnabled(true);
                    saveMenuItem.setVisible(true);
                } else {
                    saveMenuItem.setEnabled(false);
                    saveMenuItem.setVisible(false);
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
                if(s.length() == 11 && firstName.getText().toString().length() > 0 && lastName.getText().toString().length() > 0
                        && postcode.getText().toString().length() >= 6 && postcode.getText().toString().length() <= 8) {
                    saveMenuItem.setEnabled(true);
                    saveMenuItem.setVisible(true);
                } else {
                    saveMenuItem.setEnabled(false);
                    saveMenuItem.setVisible(false);
                }
            }
        });

        //add text changed listener to postcode text edit field
        postcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() >=6 && s.length() <=8 && firstName.getText().toString().length() > 0 && lastName.getText().toString().length() > 0
                        && number.getText().toString().length() == 11) {
                    saveMenuItem.setEnabled(true);
                    saveMenuItem.setVisible(true);
                } else {
                    saveMenuItem.setEnabled(false);
                    saveMenuItem.setVisible(false);
                }
            }
        });
    }

    //add menu layout to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);

        saveMenuItem = menu.findItem(R.id.action_save_contact);
        saveMenuItem.setVisible(false);
        saveMenuItem.setEnabled(false);

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

        return super.onOptionsItemSelected(item);
    }

    //save the entered data in the contact object to Realm database
    public void onClickSaveContact(MenuItem menuItem) {

        String firstNameData = firstName.getText().toString().trim();
        String lastNameData = lastName.getText().toString().trim();
        String numberData = number.getText().toString().trim();

        String emailData = email.getText().toString().trim();
        String addressData = address.getText().toString().trim();
        String postcodeData = postcode.getText().toString().trim();

        //create new contact object using entered data
        Contact contact = new Contact(firstNameData, lastNameData, numberData, emailData, addressData, postcodeData);
        ContactsData contactsData =  new ContactsData();

        //save contact to Realm
        contactsData.saveContact(contact);

        //open a new activity with contact list
        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
        startActivity(intent);

    }

    //return to previous activity by clicking back button in toolbar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }



}
