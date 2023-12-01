package com.udacity.jwdnd.course1.cloudstorage.utils;


public enum SUCCESS {
    CREDENTIAL_CREATED {
        public String toString() {
            return "Credential created successfully";
        }
    },
    CREDENTIAL_UPDATED {
        public String toString() {
            return "Credential updated successfully";
        }
    },
    CREDENTIAL_DELTED {
        public String toString() {
            return "Credential deleted successfully";
        }
    },
    NOTE_CREATED {
        public String toString() {
            return "Note added successfully";
        }
    },
    NOTE_UPDATED {
        public String toString() {
            return "Note updated successfully";
        }
    },
    NOTE_DELETED {
        public String toString() {
            return "Note deleted successfully";
        }
    },
    FILE_UPLOADED {
        public String toString() {
            return "File uploaded successfully";
        }
    },
    FILE_DELETED {
        public String toString() {
            return "File deleted successfully";
        }
    }

}
