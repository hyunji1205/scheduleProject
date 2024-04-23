package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class Calender {
    public static void calender(HashMap<String, ArrayList<String>> listMap, int year, int month) {
        System.out.println(" " + year + "년 " + month + "월의 달력");

        int sum = 0;

        for (int i = 1583; i < year; i++) {
            if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) {
                // 윤년이라면
                sum += 2;
            } else {
                // 평년이라면
                sum += 1;
            }
        }

        int first = (sum + 6) % 7; // 입력한 year의 1월 1일의 요일

        for (int j = 1; j < month; j++) {
            first += monthDay(year, j) % 7;
        }

        int day = first % 7; // 입력한 month의 1일의 요일
        int num = 1; // month의 일 표시
        int max = monthDay(year, month); // 해당 month가 가지는 최대 일 수
        boolean start = false;

        System.out.println(" ----------------------------------");
        System.out.println("  일   월   화   수   목   금   토 ");
        System.out.println(" ----------------------------------");

        loop: for (int row = 0; row <= 5; row++) {
            for (int column = 0; column <= 6; column++) {
                if (row == 0 && !start) {
                    // 달력의 첫 행
                    if (column == day) {
                        // 시작 일에 도달하면
                        start = true;
                    } else {
                        // 시작 일에 도달 전에는 공백
                        System.out.print("     ");
                        continue;
                    }
                }

                // 10이하의 month, num과 listMap에 저장된 month, day와의 자릿수 차이를 해결(ex. 2와 02의 자릿수 차이)
                String monthString = Integer.toString(month);
                String dayString = Integer.toString(num);

                if(month < 10) {
                    monthString = "0" + Integer.toString(month);
                }

                if(num < 10) {
                    dayString = "0" + Integer.toString(num);
                }

                String date = year + "-" + monthString + "-" + dayString;

                if (listMap.containsKey(date)) {
                    // if plans exist
                    System.out.printf("  %02d.", num);
                } else {
                    // if plans not exist
                    System.out.printf("  %02d ", num);
                }

                num++;

                if (num > max) {
                    // 최대 일 수에 도달하면 break loop
                    System.out.println("");
                    break loop;
                }
            }
            System.out.println("");
        }

    }
    public static int monthDay(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
            // 1월, 3월, 5월, 7월, 8월, 10월, 12월은 31일이므로, 해당 조건이 만족될 경우 31을 반환
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
            // 4월, 6월, 9월, 11월은 30일이므로, 이 조건에 해당할 경우 30을 반환
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                return 29; // 윤년을 판별하는 조건
                // 윤년은 4년마다 오지만, 100년마다 빠지고 400년마다 다시 추가.
                // 이 조건을 만족하면 2월은 29일
            } else {
                return 28; // 윤년이 아닌 경우, 2월은 28일
            }
        }
    }
}
