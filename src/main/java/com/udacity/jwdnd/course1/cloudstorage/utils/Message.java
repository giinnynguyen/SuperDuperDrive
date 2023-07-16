package com.udacity.jwdnd.course1.cloudstorage.utils;

public class Message {
    public static String[] homeMessages() {
        return new String[] {
          SUCCESS.CREDENTIAL_CREATED.toString(),
          SUCCESS.CREDENTIAL_UPDATED.toString(),
          SUCCESS.CREDENTIAL_DELTED.toString(),
          ERROR.SOMETHING_WRONG.toString(),
          SUCCESS.NOTE_CREATED.toString(),
          SUCCESS.NOTE_UPDATED.toString(),
          SUCCESS.NOTE_DELETED.toString(),
          SUCCESS.FILE_UPLOADED.toString(),
          SUCCESS.FILE_DELETED.toString(),
          ERROR.FILE_EXISTS.toString(),
          ERROR.FILE_EMPTY.toString(),
          ERROR.FILE_NOT_FOUND.toString()
        };
    }
}
