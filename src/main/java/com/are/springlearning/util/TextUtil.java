package com.are.springlearning.util;

public class TextUtil {

    private TextUtil(){

    }

    public static boolean isEmpty(CharSequence charSequence){
        return charSequence == null || charSequence.length() == 0;
    }

}
