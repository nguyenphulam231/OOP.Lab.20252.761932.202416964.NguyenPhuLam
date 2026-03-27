package exercise6_4;

import java.util.Scanner;

public class DaysNumberofMonth {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthInput;
        int year = -1;
        int month = -1;

        while (true) {
  
            System.out.print("Month (Full name/3 first characters/Number): ");
            monthInput = scanner.nextLine().trim();
            month = getMonthNumber(monthInput);

            System.out.print("Year (Non-negative, all digits): ");
            String yearInput = scanner.nextLine().trim();
            
            try {
                if (yearInput.matches("\\d+") && yearInput.length() >= 4) {
                    year = Integer.parseInt(yearInput);
                } else {
                    year = -1;
                }
            } catch (NumberFormatException e) {
                year = -1;
            }

            if (month != -1 && year >= 0) {
                int days = calculateDays(month, year);
                System.out.println("Days number of month " + monthInput + " year " + year + " is: " + days);
                break;
            } else {
                System.out.println("Invalid year or month. Please type again!\n");
            }
        }
        scanner.close();
    }

    private static int getMonthNumber(String input) {
        input = input.toLowerCase();
        
        String[][] monthPatterns = {
            {"january", "jan.", "jan", "1"},
            {"february", "feb.", "feb", "2"},
            {"march", "mar.", "mar", "3"},
            {"april", "apr.", "apr", "4"},
            {"may", "may", "may", "5"},
            {"june", "jun.", "jun", "6"},
            {"july", "jul.", "jul", "7"},
            {"august", "aug.", "aug", "8"},
            {"september", "sept.", "sep", "9"},
            {"october", "oct.", "oct", "10"},
            {"november", "nov.", "nov", "11"},
            {"december", "dec.", "dec", "12"}
        };

        for (int i = 0; i < 12; i++) {
            for (String pattern : monthPatterns[i]) {
                if (input.equals(pattern)) {
                    return i + 1;
                }
            }
        }
        return -1; 
    }

    private static int calculateDays(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 31;
        }
    }
}