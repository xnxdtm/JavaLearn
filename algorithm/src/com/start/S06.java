package com.start;

import static com.Tool.*;

/**
 * 题目: 插入排序
 * 思路: 假设前n个数有序, 则下一个循环保证前n+1个数有序
 */
public class S06 {
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
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    arr[j + 1] = arr[j];
                    if (j == 0) arr[j] = arr[i];
                } else {
                    arr[j] = target;
                }
            }
        }
    }

    public static void myInitCode(int[] arr2) {

    }


}
