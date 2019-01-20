package com.example.romanromanov.contactsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private Realm realm = ContactListApplication.realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the toolbar from layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listContacts = findViewById(R.id.contact_list);

        //get the full names of contacts in the Realm database
        ContactsData contactsData = new ContactsData();
        ArrayList<String> contactsNames = contactsData.retrieveNames();

        //create and set the adapter
        ArrayAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsNames);
        listContacts.setAdapter(listAdapter);

        //create on item click listener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    intent = new Intent(MainActivity.this, ContactActivity.class);
                    intent.putExtra(ContactActivity.EXTRA_CONTACTID, (int) id);
                    startActivity(intent);

            }
        };

        listContacts.setOnItemClickListener(itemClickListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.call_white);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KeypadActivity.class);
                startActivity(intent);
            }
        });
    }

    //used for menu button in the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_new_contact) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //add contact button opens a new activity
    public void onClickAddNewContact(MenuItem menuItem) {
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(intent);
    }
}
