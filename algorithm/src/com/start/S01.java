package com.start;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 题目: 打印一个整数的32个bit位
 * 思路: 1 >> i 位后与&原数即得到了i位置是否是1
 * 负数: 负数的绝对值 = 负数的二进制取反+1 得到的值, 并且最高位是符号位 1表示负数
 * 负数这么设计是为了保证+-*\/底层用一套逻辑
 */
public class S01 {
    public static void main(String[] args) {
        int num = 1151234;
        myInitCode(num);
        standardCode(num);
    }

    public static void standardCode(int num) {
        System.out.println("**************** standardCode com.start ****************");
        for (int i = 31; i >= 0; i--) {
            System.out.print(((1 << i) & num) > 0 ? 1 : 0);
        }
        System.out.println();
        System.out.println("**************** standardCode over ****************");
    }

    public static void myInitCode(int num) {
        System.out.println("**************** myInitCode com.start ****************");
        ArrayList<Integer> bitArr = new ArrayList<>();
        while (num > 0) {
            // 计算最后一个bit位
            int r1 = num >> 1;
            int temp = r1 << 1;
            int lastBit = num ^ temp;
            bitArr.add(lastBit);
            // 原数值去掉最后一个bit位
            num = r1;
        }
        Collections.reverse(bitArr);
        bitArr.forEach(System.out::print);
        System.out.println();
        System.out.println("**************** myInitCode over ****************");
    }


}
