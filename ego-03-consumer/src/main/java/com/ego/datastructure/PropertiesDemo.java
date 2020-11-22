package com.ego.datastructure;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class PropertiesDemo {
    public static void main(String[] args) {
        /**
         * 类表示一组持久的属性。
         * {@code Properties}可以保存到流中，也可以从流中加载。
         * 属性列表中的每个键及其对应值都是一个字符串。
         */
        Properties capitals = new Properties();
        capitals.put("Illinois", "Springfield");
        capitals.put("Missouri", "Jefferson City");
        capitals.put("Washington", "Olympia");
        capitals.put("California", "Sacramento");
        capitals.put("Indiana", "Indianapolis");
        // Show all states and capitals in hashtable.
        // get set-view of keys
        Set<Object> states = capitals.keySet();
        Iterator<Object> iterator = states.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            System.out.println("The capital of " + string + " is " + capitals.getProperty(string) + ".");
        }
        System.out.println();
        // look for state not in list -- specify default
        String string = capitals.getProperty("Florida", "Not Found");
        System.out.println("The capital of Florida is " + string + ".");
    }
}
