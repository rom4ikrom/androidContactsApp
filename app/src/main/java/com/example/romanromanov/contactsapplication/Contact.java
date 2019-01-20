package com.example.romanromanov.contactsapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import io.realm.RealmObject;

public class Contact extends RealmObject implements Serializable {


    private String firstName;
    private String lastName;
    private String number;

    private String email;
    private String address;
    private String postcode;

    /*
    //Test data which was created for testing purpose
    //no more needed as Realm database is implemented into the project
    public static Contact[] contactsArray = {
            new Contact("Matilda", "Brown", "12345", "matilda.brown@gmail.com",
                    "12 Park Street", "AA4 5DD"),
            new Contact("John", "Smith", "54321", "john.smith@gmail.com",
                    "15 Bridge Road", "ED45 7TG"),
            new Contact("Roman", "Romanov", "56789", "roman.romanov@gmail.com",
                    "55 High Street", "TW5 77FG")
    };

    public static ArrayList<Contact> contacts = new ArrayList<>(Arrays.asList(contactsArray));
    */

    public Contact() {

    }

    public Contact(String firstName, String lastName, String number, String email, String address, String postcode){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNumber(number);
        this.setEmail(email);
        this.setAddress(address);
        this.setPostcode(postcode);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

}
