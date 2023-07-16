package com.udacity.jwdnd.course1.cloudstorage.utils;

public enum ERROR {
    SOMETHING_WRONG {
        public String toString() {
            return "Something went wrong.";
        }
    },
    FILE_EXISTS {
        public String toString() {
            return "File already exists.";
        }
    },
    FILE_EMPTY {
        public String toString() {
            return "File is empty.";
        }
    },
    FILE_NOT_FOUND {
        public String toString() {
            return "File not found.";
        }
    },
}
