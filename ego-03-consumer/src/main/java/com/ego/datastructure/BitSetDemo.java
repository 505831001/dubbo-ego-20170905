package com.ego.datastructure;

import java.util.BitSet;

/**
 * @author liuweiwei@163.com
 * @since 2020-11-18
 */
public class BitSetDemo {
    public static void main(String[] args) {
        /**
         * 这个类实现了一个根据需要增长的位向量。
         * 位集的每个组件都有一个{@code boolean}值。
         * {@code BitSet}的位被非负整数索引。
         * 单个索引位可以被检查、设置或清除。
         * 一个{@code BitSet}可以通过逻辑和、逻辑包容或和逻辑排他或操作来修改另一个{@code BitSet}的内容。
         */
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);
        // set some bits
        for(int i=0; i<16; i++) {
            if((i%2) == 0) {bits1.set(i);}
            if((i%5) != 0) {bits2.set(i);}
        }
        System.out.println("\nInitial pattern in bits1: " + bits1);
        System.out.println("\nInitial pattern in bits2: " + bits2);
        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: " + bits2);
        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: " + bits2);
        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: " + bits2);
    }
}
