package com.example.romanromanov.contactsapplication;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

//acts as a data access intermediate to access Drink data held in the Realm database

public class ContactsData {

    private Realm realm;

    public ContactsData() {
        this.realm = ContactListApplication.realm;
    }

    //saves the contact object to Realm database
    public void saveContact(Contact contact) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.copyToRealm(contact);
                realm.insert(contact);
            }
        });
    }

    //delete the object from Realm
    public void removeContact(Contact contact) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                contact.deleteFromRealm();
            }
        });
    }

    //retrieves full names of contacts from Realm to display on Main Activity
    public ArrayList<String> retrieveNames() {
        ArrayList<String> contactsNames = new ArrayList<>();
        RealmResults<Contact> contacts = realm.where(Contact.class).sort("number").findAll();

        for(Contact c : contacts) {
            contactsNames.add(c.getFirstName() + " " + c.getLastName());
        }
        return contactsNames;
    }

    //get the index of contact from the RealmResults (list) sorted by last name of contacts
    public int getContactIndex(Contact contact) {
        RealmResults<Contact> contacts = realm.where(Contact.class).sort("number").findAll();
        System.out.println(contacts.toString());
        return contacts.indexOf(contact);
    }

    //get the contact from list based on the given index
    public Contact getContact(int index) {
        RealmResults<Contact> contacts = realm.where(Contact.class).sort("number").findAll();
        return contacts.get(index);
    }
}
