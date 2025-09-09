package com.yeonsoo.section01.array.level02.normal;

import java.util.Arrays;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        /* 주민등록번호를 스캐너로 입력 받고 문자 배열로 저장한 뒤,
         * 성별 자리 이후부터 *로 가려서 출력하세요
         *
         * -- 입력 예시 --
         * 주민등록번호를 입력하세요 : 990101-1234567
         *
         * -- 출력 예시 --
         * 990101-1******
         */

        Scanner sc = new Scanner(System.in);
        System.out.print("주민등록번호를 입력하세요 : ");
        String num = sc.next();

        char[] arr = num.toCharArray();

        char[] numCopy1 = Arrays.copyOf(arr, 6);
        char[] numCopy2 = new char[1];

        System.arraycopy(arr, 7, numCopy2, 0, 1);
        System.out.print(String.valueOf(numCopy1)+"-"+String.valueOf(numCopy2)+"******");

    }
}
