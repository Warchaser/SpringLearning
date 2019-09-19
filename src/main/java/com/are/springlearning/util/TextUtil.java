package com.are.springlearning.util;

public class TextUtil {

    private TextUtil(){

    }

    public static boolean isEmpty(CharSequence charSequence){
        return isNull(charSequence) || charSequence.length() == 0;
    }

    public static boolean isNull(CharSequence charSequence){
        return charSequence == null;
    }

}
