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
    }
}
