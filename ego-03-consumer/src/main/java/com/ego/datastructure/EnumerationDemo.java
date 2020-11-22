package com.ego.datastructure;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class EnumerationDemo {
    public static void main(String[] args) {
        /**
         * 实现枚举接口的对象生成一系列元素，一次一个。
         * 连续调用<code>nextElement</code>方法返回序列的连续元素。
         */
        Enumeration<String> elements;
        /**
         * 类实现了一个可增长的对象数组。
         * 与数组一样，它包含可以使用整数索引访问的组件。
         * 但是，{@code Vector}的大小可以根据需要增大或缩小，以适应添加和删除{@code Vector}之后的项目。
         */
        Vector<String> vector = new Vector<>();
        vector.add("Sunday");
        vector.add("Monday");
        vector.add("Tuesday");
        vector.add("Wednesday");
        vector.add("Thursday");
        vector.add("Friday");
        vector.add("Saturday");
        elements = vector.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
    }
}
