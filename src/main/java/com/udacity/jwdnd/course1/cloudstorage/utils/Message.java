package com.udacity.jwdnd.course1.cloudstorage.utils;

public class Message {
    public static String[] homeMessages() {
        return new String[] {
          SUCCESS.CREDENTIAL_CREATED.toString(),
          SUCCESS.CREDENTIAL_UPDATED.toString()
        };
    }

    enum ERROR {
        CREDENTIAL_CREATED {
            public String toString() {
                return "Credential created unsuccessfully";
            }
        },
    }

}
