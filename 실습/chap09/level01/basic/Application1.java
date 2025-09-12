package com.yeonsoo.level01.basic;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        /* ----- 입력 예시 -----
         *
         * 문자열 입력 : I will be a good developer.
         *
         * ----- 출력 예시 -----
         *
         * 변환된 문자열: I Will Be A Good Developer.
         * 총 단어 개수: 6
         */

        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 입력 : ");
        String text =  sc.nextLine();

        String[] textArr =  text.split(" ");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < textArr.length; i++) {
            System.out.println(textArr[i]);
            if (!textArr[i].isEmpty()) {
                String upperCase = textArr[i].substring(0,1).toUpperCase() +  textArr[i].substring(1);
                result.append(upperCase);

                if (i < textArr.length - 1) {
                    result.append(" ");
                }
            }
        }

        System.out.println("변환된 문자열: " + result.toString());
        System.out.println("총 단어 개수: " + textArr.length);

    }
}
