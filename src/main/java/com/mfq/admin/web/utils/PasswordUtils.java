package com.mfq.admin.web.utils;

import com.mfq.admin.web.constants.AdminConstants;

public class PasswordUtils {

    public static String encode(String password) {
        return password.length() == 32 ? password : Md5.crypt(password + AdminConstants.PASSWORD_SALT);
    }
    
    //14bc9cd1705c7d7fc8985a88d6b36bc9
    public static void main(String[] args){
        System.out.println(encode("123"));
    }
}
