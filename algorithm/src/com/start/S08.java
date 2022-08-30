package com.start;

/**
 * 题目:
 * 1. 如何获取一个随机数
 * 2. a,b,x属于[0,1), 且a,b落在[0,x)的概率为x, 如何让a,b落在[0,x)的概率为x^2
 * 思路:
 * 1. 随机数用Math.random()
 * 2. 两个数a,b在[0,1)上的情况有三种
 *      a [0,x)   b [0,x)
 *      a [0,x)   b [x,1)
 *      a [x,1)   b [x,1)
 */
public class S08 {
    public static void main(String[] args) {
        int times = 1000000;
        int count = 0;
        int x = 8;
        for (int i = 0; i < times; i++) {
            if (randomInt(10) < x) count++;
        }
        System.out.println((double)count / (double)times);
        System.out.println((double)8 / (double)10);

        System.out.println("=========");
        count = 0;
        for (int i = 0; i < times; i++) {
            if (xToPow2() < 0.3) count++;
        }
        System.out.println((double)count / (double)times);
        System.out.println(Math.pow(0.3, 2));

    }

    public static int randomInt(int x) {
        return (int) (Math.random() * x);
    }

    /**
     * @return 返回一个[0,1)的小数, 小数落在[0,x)的概率为 x^2   x属于(0,1)
     */
    public static double xToPow2() {
        return Math.max(Math.random(), Math.random());
    }

    /**
     * Math.min(a, b)    a[0,x) b[0,x)
     * 概率相当于
     * Math.max(c, d)    c[x,1) b[x,1)
     * 分布位置:
     * 0--a---b----x------------1
     * 0----a------x----b-------1
     * 0-----------x----a---b---1
     */
    public static double xToPow() {
        return Math.min(Math.random(), Math.random());
    }
}
