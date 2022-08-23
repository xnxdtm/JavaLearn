package com.start;


import static com.Tool.*;

/**
 * 题目: 选择排序
 * 思路: 每次拿出剩余数中的最大的那个数放在最前面或最后面
 */
public class S03 {
    public static void main(String[] args) {
        int times = 1000;
        boolean flag = true;
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(1000, 10000);
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            standardCode(arr1);
            myInitCode(arr2);
            if (!isSameArr(arr1, arr2)) flag = false;
        }
        printByBoolean(flag);
    }

    public static void myInitCode(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int maxIndex = 0;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            swapArr(arr, arr.length - 1 - i, maxIndex);
        }
    }

    public static void standardCode(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int maxIndex = 0;
            for (int j = 1; j < N - i; j++) {
                maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;
            }
            swapArr(arr, N - 1 - i, maxIndex);
        }
    }

}
