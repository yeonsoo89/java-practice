package com.yeonsoo.section01.array.level02.normal;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        /*
         * 문자열을 하나 입력받아 문자 자료형 배열로 바꾼 뒤
         * (String Class의 toCharArray() : char[] 메소드 활용 가능
         * char[] carr = 문자열변수.toCharArray(); 와 같이 사용)
         * 검색할 문자를 하나 입력 받아 입력 받은 검색할 문자가
         * 문자열에 몇개 있는지를 출력하세요
         *
         * -- 입력 예시 --
         * 문자열을 하나 입력하세요 : helloworld
         * 검색할 문자를 입력하세요 : l
         *
         * -- 출력 예시 --
         * 입력하신 문자열 helloworld에서 찾으시는 문자 l은 3개 입니다.
         */

        Scanner sc = new Scanner(System.in);

        System.out.print("문자열을 하나 입력하세요 : ");
        String target =  sc.next();

        char[] carr = target.toCharArray();

        System.out.print("검색할 문자를 입력하세요 : ");
        String word =  sc.next();

        int num = 0;

        for (int i = 0; i < carr.length; i++) {
            if (carr[i] == word.charAt(0)) {
                num++;
            }
        }

        System.out.println("입력하신 문자열 " + target + "에서 찾으시는 문자 " + word + "은 " + num + "개 입니다.");

    }
}
