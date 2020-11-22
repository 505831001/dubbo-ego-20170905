package com.ego.datastructure;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class StackDemo {
    static void showpush(Stack<Integer> stack, int a) {
        stack.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + stack);
    }
    static void showpop(Stack<Integer> stack) {
        System.out.print("pop -> ");
        Integer a = (Integer) stack.pop();
        System.out.println(a);
        System.out.println("stack: " + stack);
    }
    public static void main(String[] args) {
        /**
         * The Stack栈类表示对象的后进先出栈。
         * 它通过五个操作扩展了类Vector，这些操作允许将向量作为堆栈处理。
         * 通常的push和流行推提供了pop操作,以及方法peek在项堆栈顶部,一个方法来测试是否栈是空empty,和< tt >方法搜索search堆栈的项目和发现从顶部有多远。
         * 第一次创建堆栈时，它不包含任何项。
         */
        Stack<Integer> stack = new Stack<>();
        System.out.println("stack: " + stack);
        showpush(stack, 42);
        showpush(stack, 66);
        showpush(stack, 99);
        showpop(stack);
        showpop(stack);
        showpop(stack);
        try {
            showpop(stack);
        } catch (EmptyStackException e) {
            System.out.println("empty stack");
        }
    }
}
