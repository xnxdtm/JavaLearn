package com.start;

/**
 * 题目: 题目: 有一个随机发生器生成 1 的概率为 p 生成 0 的概率为 1-p, 根据这个发生器设计一个 0-1 等概发生器
 * 思路: p*p p*(1-p) (1-p)*p (1-p)*(1-p) 中间两个是等概率的
 */
public class S10 {
    public static void main(String[] args) {
        int times = 10000;
        int count = 0;
        for (int i = 0; i < times; i++) {
            if (f(0.3) == 1) count++;
        }
        System.out.println((double) count / (double) times);

        System.out.println("=======");
        count = 0;
        for (int i = 0; i < times; i++) {
            if (g() == 1) count++;
        }
        System.out.println((double) count / (double) times);
    }


    /**
     * 等概0-1发生器
     * @return
     */
    public static int g() {
        int ans = 0;
        do {
            ans = f(0.3);
        } while (ans == f(0.3));
        return ans;
    }

    /**
     * 概率为p的 0-1 发生器
     * @return
     */
    public static int f(double p) {
        return Math.random() > p ? 1 : 0;
    }

}
