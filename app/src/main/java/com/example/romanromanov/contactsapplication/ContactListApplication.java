package com.example.romanromanov.contactsapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ContactListApplication extends Application {

    public static Realm realm;

    /*
    Run when application is launched
    Sets up the Realm database and stores the instance of Realm as a static variable
     */

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("contacts.realm")
                .schemaVersion(0)
                //.migration(new RealmMigrations())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
    }

    //close the realm database
    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}
