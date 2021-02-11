package io.github.taoscode.conc0303.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(4,"val");
        map.put(1,"val");
        map.put(6,"val");
        map.put(3,"val");
        map.put(2,"val");
        map.put(5,"val");
        System.out.println(map);
        TreeMap<Integer,String> map2 = new TreeMap<>(Comparator.naturalOrder());
        map2.putAll(map);
        System.out.println(map2);
    }
}
