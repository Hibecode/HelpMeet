package com.example.helpmeet.utils

import java.util.regex.Pattern

class Constants {

    companion object{

        const val password_warning = "Password must contain Min. " +
                "8 characters, " +
                "1 Uppercase, 1 lowercase, " +
                "1 number, and 1 special character"

        val PASSWORD_PATTERN: Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$")
    }
}