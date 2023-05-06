package com.project.underline.user.web.dto;


import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SignupRequestDtoTest {

    @Test
    void passwordTest() {
        String[] list = {"123adsfsdfasdfadfsda", "abc", "Abc", "abcddeee12312", "a!@#$%^^&**a1*((),./';", "123!@#dsasAAAAA", "ASDFSAsdf12@#!%13sdds"};

        for (String s : list) {
            System.out.println(isValid(s));
        }
    }

    private boolean isValid(String password) {
        boolean ret = false;
        String regex = "^(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d~!@#$%^&*(){}:;',./\\-+\"?><]{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(m.matches()) {
            ret = true;
        }
        return ret;
    }

}