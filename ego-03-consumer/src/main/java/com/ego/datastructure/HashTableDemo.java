package com.ego.datastructure;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class HashTableDemo {
    public static void main(String[] args) {
        /**
         * 这个类实现了一个哈希表，它将键映射到值。
         * 任何非null对象都可以用作键或值。
         * 要成功地从散列表中存储和检索对象，作为键的对象必须实现hashCode方法和equals法。
         */
        Hashtable hashtable = new Hashtable();
        hashtable.put("Zara", new Double(3434.34));
        hashtable.put("Mahnaz", new Double(123.22));
        hashtable.put("Ayan", new Double(1378.00));
        hashtable.put("Daisy", new Double(99.22));
        hashtable.put("Qadir", new Double(-19.08));
        // Show all balances in hash table.
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String string = (String) keys.nextElement();
            System.out.println(string + ": " + hashtable.get(string));
        }
        // Deposit 1,000 into Zara's account
        Double aDouble = ((Double) hashtable.get("Zara")).doubleValue();
        hashtable.put("Zara", new Double(aDouble + 1000));
        System.out.println("Zara's new balance: " + hashtable.get("Zara"));
    }
}
