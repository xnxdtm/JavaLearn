package com.start;

/**
 * 题目: 给定一个整数N 计算 1! + 2! + 3! + 4! + ... + N!
 * 思路: 对每一项进行存储, 后一项就是前一项在乘以后一个数
 */
public class S02 {
    public static void main(String[] args) {
        myInitCode(4);
        standardCode(4);
    }

    public static void standardCode(int num) {
        System.out.println("**************** standardCode com.start ****************");

        int res = 0;
        int temp = 1;
        for (int i = 1; i <= num; i++) {
            temp *= i;
            res += temp;
        }
        System.out.println(res);

        System.out.println();
        System.out.println("**************** standardCode over ****************");
    }

    public static void myInitCode(int num) {
        System.out.println("**************** myInitCode com.start ****************");

        int res = 0;
        for (int i = 1; i <= num; i++) {
            int temp = 1;
            for (int j = 1; j <= i; j++) {
                temp *= j;
            }
            res += temp;
        }
        System.out.println(res);

        System.out.println("**************** myInitCode over ****************");
    }


}
