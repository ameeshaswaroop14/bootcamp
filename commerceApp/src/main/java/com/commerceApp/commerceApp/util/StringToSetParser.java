package com.commerceApp.commerceApp.util;


import java.util.HashSet;
import java.util.Set;

public class StringToSetParser {

    public static Set<String> toSetOfValues(String value){
        Set<String> values = new HashSet<>();
        String[] splitValues = value.split(",");

        for(String splitValue : splitValues){
            values.add(splitValue);
        }
        return values;
        //for admin response dto
    }

    public static String toCommaSeparatedString(Set<String> valueSet){
        String values = "";
        if(valueSet==null || valueSet.isEmpty())
            return values;

        values = String.join(",", valueSet);
        return values;
    }
    //for saving metadata field values

}
