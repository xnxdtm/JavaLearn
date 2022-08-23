package com.start;

import static com.Tool.*;

/**
 * 题目: 冒泡排序
 * 思路: 对比相邻两个数, 大的后面或小的放前面
 */
public class S04 {
    public static void main(String[] args) {
        int times = 1000;
        boolean flag = true;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(10, 10);
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            standardCode(arr1);
            sortArr(arr2);
            if (!isSameArr(arr1, arr2)) {
                printArr(arr1);
                printArr(arr2);
                flag = false;
            }
        }
        printByBoolean(flag);
    }

    public static void standardCode(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swapArr(arr, j, j + 1);
            }
        }
    }

    public static void myInitCode(int[] arr2) {

    }


}
