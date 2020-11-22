package com.ego.datastructure;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class VectorDemo {
    public static void main(String[] args) {
        /**
         * 构造一个具有指定初始容量和容量增量的空向量。
         * @param initialCapacity   向量的初始容量
         * @param capacityIncrement 矢量溢出时容量增加的数量。
         */
        Vector vector = new Vector(3, 2);
        System.out.println("Initial size: " + vector.size());
        System.out.println("Initial capacity: " + vector.capacity());

        vector.addElement(new Integer(1));
        vector.addElement(new Integer(2));
        vector.addElement(new Integer(3));
        vector.addElement(new Integer(4));
        System.out.println("Capacity after four additions: " + vector.capacity());

        vector.addElement(new Double(5.45));
        System.out.println("Current capacity: " + vector.capacity());

        vector.addElement(new Double(6.08));
        vector.addElement(new Integer(7));
        System.out.println("Current capacity: " + vector.capacity());

        vector.addElement(new Float(9.4));
        vector.addElement(new Integer(10));
        System.out.println("Current capacity: " + vector.capacity());

        vector.addElement(new Integer(11));
        vector.addElement(new Integer(12));
        System.out.println("First element: " + (Integer) vector.firstElement());
        System.out.println("Last element: " + (Integer) vector.lastElement());
        // 枚举向量中的元素。
        if (vector.contains(new Integer(3))) {
            System.out.println("Vector contains 3.");
        }
        Enumeration vEnum = vector.elements();
        System.out.println("\nElements in vector:");
        while (vEnum.hasMoreElements()) {
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();
    }
}
