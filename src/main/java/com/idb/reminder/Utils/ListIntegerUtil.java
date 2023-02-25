package com.idb.reminder.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIntegerUtil {
    public List<Integer> stringToListInteger(String input) {
        
        List<Integer> itemIntegers = new ArrayList<Integer>();

        if(input != null) {
            if(!input.isBlank()) {
                List<String> itemStrs = Arrays.asList(input.split(";"));
                for (String string : itemStrs) {
                    try {
                        Integer num = Integer.parseInt(string);
    
                        itemIntegers.add(num);
                    } catch (Exception e) {
                        // TODO: handle exception
                        // e.printStackTrace();
                    }
                }
            }
        }

        return itemIntegers;
    }

    public String listIntegerToString(List<Integer> input) {
        String result = "";

        for (Integer integer : input) {
            result = result.isBlank() ? "" + integer : result + ";" + integer;
        }

        return result;
    }
}
