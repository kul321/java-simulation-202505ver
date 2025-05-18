package 진도.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args){
     ArrayList<String> colors = new ArrayList<>(Arrays.asList("Black", "White","Green","Red"));
     boolean contains = colors.contains("Black");
        System.out.println(contains);

        int index = colors.indexOf("Blue");
        System.out.println(index);

        index = colors.indexOf("Red");
        System.out.println(index);
    }
}
