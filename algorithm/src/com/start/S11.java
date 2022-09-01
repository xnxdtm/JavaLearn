package com.start;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.Tool.*;

/**
 * 题目: 有序数组中寻找是否存在指定值num
 * 思路:
 */
public class S11 {
    public static void main(String[] args) {
        int times = 10000;
        int[] arr1 = {16};
        int num1 = 7;
        f(arr1, num1);
        for (int i = 0; i < times; i++) {
            int[] arr = randomArr(30, 20);
            sortArr(arr);
            int num = (int)(Math.random() * 20);
            // 一个一个对比是否存在
            boolean exist1 = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == num) {
                    exist1 = true;
                    break;
                }
            }
            // 二分法对比是否存在
            boolean exist2 = f(arr, num);
            if (exist2 != exist1) {
                System.out.print("");
                printArr(arr);
                System.out.println("num: " + num);
                System.out.println("index1: " + exist1);
                System.out.println("index2: " + exist2);
            }
        }
    }

    public static boolean f(int[] arr, int num) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        if (N == 0) return false;
        while (R >= L) {
            int mid = (R + L) >> 1;  // 3,4 => 3  4,5 => 4   3,5 => 4  3,3 => 3
            if (num > arr[mid]) {
                L = mid + 1;
            } else if (num == arr[mid]) {
                return true;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

}
