package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Calender {
    public static void calender(HashMap<String, ArrayList<String>> listMap, int year, int month) {
        System.out.println("            " + year + "년 " + month + "월");

        int maxDays = monthDay(year, month); // 월의 최대 일 수

        System.out.println(" ----------------------------------");
        System.out.println("  일   월   화   수   목   금   토 ");
        System.out.println(" ----------------------------------");

        int day = LocalDate.of(year, month, 1).getDayOfWeek().getValue() % 7; // 월의 1일 요일
        int num = 1; // 날짜 초기화

        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                if (row == 0 && column < day) {
                    System.out.print("     ");
                    continue;
                }

                if (num <= maxDays) {
                    System.out.printf("  %02d ", num);
                    num++;
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("");
        }
    }
    public static int monthDay(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return 29; // 윤년
            } else {
                return 28; // 평년
            }
        }
    }
}
