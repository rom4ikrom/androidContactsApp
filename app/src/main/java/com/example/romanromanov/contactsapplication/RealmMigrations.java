package com.example.romanromanov.contactsapplication;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class RealmMigrations implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        /*
        this class is used to update the realm database if there is a need to add or change some attributes that
        are already in realm
         */


        /*
        final RealmSchema schema = realm.getSchema();
        if(oldVersion == 0) {
            schema.get("Contact")
                    .addField("favourite", boolean.class);
            oldVersion++;
        }
        */

    }
}
