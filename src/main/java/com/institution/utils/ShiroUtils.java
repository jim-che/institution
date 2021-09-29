package com.institution.utils;


import com.institution.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author chenguo
 */
public class ShiroUtils {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
