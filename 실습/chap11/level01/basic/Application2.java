package com.ohgiraffers.level01.basic;

import java.io.*;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        /* ----- 입력 예시 -----
         *
         * (원본 파일이 존재 하는 경우)
         * 원본 파일의 이름을 입력하세요 : origin.txt
         * 복사 파일의 이름을 입력하세요 : copy.txt
         *
         * ----- 출력 예시 -----
         *
         * 파일 복사가 성공적으로 완료 되었습니다.
         *
         * ----- 입력 예시 -----
         *
         * (원본 파일이 존재하지 않는 경우)
         * 원본 파일의 이름을 입력하세요 : origin2.txt
         * 복사 파일의 이름을 입력하세요 : copy2.txt
         *
         * ----- 출력 예시 -----
         *
         * 오류 : origin2.txt (지정된 파일을 찾을 수 없습니다)
         *
         */

        Scanner sc = new Scanner(System.in);

        System.out.print("원본 파일의 이름을 입력하세요 : ");
        String originName =  sc.nextLine();

        System.out.print("복사 파일의 이름을 입력하세요 : ");
        String copyName =  sc.nextLine();

        File originFile =  new File(originName);
        if (originFile.exists()) {
            try (
                    BufferedReader br = new BufferedReader(new FileReader(originFile));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(copyName));
                    ) {
                String line;
                while ((line = br.readLine()) != null) {
                    bw.write(line);
                }
                System.out.println("파일 복사가 성공적으로 완료 되었습니다.");

            } catch (IOException e) {
                e.getMessage();
            }

        } else {
            System.out.println("오류 : "+ originName +"(지정된 파일을 찾을 수 없습니다)");
        }
    }
}
