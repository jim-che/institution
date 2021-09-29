package com.institution.com;

/**
 * @author chenguo
 */
public class Algorithm {
    static final String AT = "@";
    static final String DOT = ".";

    public static boolean verifyEmail(String email){
        if(!email.contains(AT)||!email.contains(DOT)){
            return false;
        }
        return email.indexOf(AT) == 0 || email.indexOf(DOT) == email.length() - 1 || email.indexOf(AT) + 1 <= email.indexOf(DOT);
    }
}
