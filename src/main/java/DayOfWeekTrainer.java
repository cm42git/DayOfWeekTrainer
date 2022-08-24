import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DayOfWeekTrainer extends DayOfWeekAlgorithm {
    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].trim().equalsIgnoreCase("-full")) {
                printAlgoHelp();
                System.exit(0);
            } else if (args[0].trim().equalsIgnoreCase("-memory")) {
                printMemoryItems();
                System.exit(0);
            } else {
                System.out.println("\nUsage:");
                System.out.println("\t<none>     Run the training program");
                System.out.println("\t-full      Display the full algorithm");
                System.out.println("\t-memory    Display memory items");
                System.exit(-1);
            }
        }

        Scanner input = new Scanner(System.in);

        System.out.println("\nWhat date are you getting the day of week for (MM/DD/YYYY)? ");
        String inputDate = input.nextLine();
        LocalDate dateToGet;
        try {
            String[] inputDateArray = inputDate.split("/");
            String inputDateForParse = inputDateArray[2] + "-" +
                    ((inputDateArray[0].length() == 1) ? "0" : "") + inputDateArray[0] + "-" +
                    ((inputDateArray[1].length() == 1) ? "0" : "") + inputDateArray[1];
            dateToGet = LocalDate.parse(inputDateForParse);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Unable to parse date. Please try again.");
            return;
        }
        finalSumUser = getFinalSumFromUser(input);

        System.out.println("Here's the results for " + dateToGet.getYear() + ":");
        System.out.println("The century number: " + centuryCalc(dateToGet));
        System.out.println("Divide by 12: " + realDivBy12(dateToGet));
        System.out.println("Remainder: " + realRemBy12(dateToGet));
        System.out.println("Remainder div by 4: " + realDivBy4(dateToGet));
        System.out.println();
        System.out.println("The real sum was: " + realFinalSum(dateToGet));
        if (realFinalSum(dateToGet) == finalSumUser) {
            System.out.println("Good job!");
        }

        finalDOWUser = getFinalDOWUser(input, inputDate);

        System.out.println();
//        System.out.println("The doomsday for " + dateToGet.getMonth() + " is: " + doomsdayOfMonth[dateToGet.getMonthValue() - 1]);
        System.out.println("The doomsday for " + dateToGet.getMonth() + " is: " + DoomsdayOfMonth.values()[dateToGet.getMonthValue() - 1].doomsday());
//        if (doomsdayOfMonth[dateToGet.getMonthValue() - 1] == doomsdayDayUser) {
        if (DoomsdayOfMonth.values()[dateToGet.getMonthValue() - 1].doomsday() == doomsdayDayUser) {
            System.out.println("Good job!");
        }

        System.out.println("The day of week index number is: " + (dateToGet.getDayOfWeek().getValue()));
        if ((dateToGet.getDayOfWeek().getValue()) == finalDOWUser) {
            System.out.println("Perfect!");
        } else {
            System.out.println("Wrong day of week.");
        }

        System.out.println();
        System.out.println("The weekday of " + dateToGet.toString() + " is " + dateToGet.getDayOfWeek());
    }


    public static int getFinalSumFromUser(Scanner input) {
        int finalSumInput = 0;

        System.out.println("What's the 400-year number?");
        int centuryNumUser = Integer.parseInt(input.nextLine());

        System.out.println("What's the year of century divided by 12?");
        int divideBy12User = Integer.parseInt(input.nextLine());

        System.out.println("What's the remainder?");
        int remainderBy12User = Integer.parseInt(input.nextLine());

        System.out.println("What's that number divided by 4?");
        int divideRemainderBy4User = Integer.parseInt(input.nextLine());

        System.out.println("What's your final sum?");
        finalSumInput = Integer.parseInt(input.nextLine());

        System.out.println("Your sum should be: " + (centuryNumUser + divideBy12User + remainderBy12User + divideRemainderBy4User));
        if (finalSumInput == (centuryNumUser + divideBy12User + remainderBy12User + divideRemainderBy4User)) {
            System.out.println("Good job!");
        }
        System.out.println();

        return finalSumInput;
    }

    private static int getFinalDOWUser(Scanner input, String date) {
        System.out.println("What is the doomsday of the month? ");
        doomsdayDayUser = Integer.parseInt(input.nextLine());

        System.out.println("With your final sum remainder from 7, what is the day of week for " + date + "?");
        System.out.println("(0=Sun, 1=Mon, 2=Tue, 3=Wed, 4=Thu, 5=Fri, 6=Sat) ");
        return Integer.parseInt(input.nextLine());
    }

    private static void printAlgoHelp() {
        System.out.println("\nThe day of week algorithm consists of 3 parts: the century, the year of century, and months in the year. " +
                "Days of the week start at zero on Sunday, one for Monday, etc. Use the following days for century numbers:\n" +
                "- 1600, 2000, 2400...: Tuesday = 2\n" +
                "- 1700, 2100, 2500...: Sunday = 0\n" +
                "- 1800, 2200, 2600...: Friday = 5\n" +
                "- 1900, 2300, 2700...: Wednesday = 3\n" +
                "\nYear of century is decoded by dividing by 12 plus remainder plus dividing remainder by 4:\n" +
                "1945: 45 / 12 = 3\n" +
                "      45 % 12 = 9\n" +
                "       9 /  4 = 2\n" +
                "    3 + 9 + 2 = 14\n" +
                "\nAdd that sum to the century number and get the remainder after dividing by 7 (3 + 14 = 17 % 7 = 3). This is the day of week of the \"doomsday\".\n" +
                "Doomsdays are the same day of week every year:\n" +
                "Jan 3, Feb 28(29), Mar 14,\n" +
                "Apr 4, May 9, Jun 6,\n" +
                "Jul 11, Aug 8, Sep 5,\n" +
                "Oct 10, Nov 11, Dec 12\n" +
                "\nFind the closest doomsday to your date, then use the doomsday of week number to find the weekday.\n" +
                "Sep 2, 1945: Doomsdays are 3 (Wed), September's doomsday is the 5th, so if 9/5 is Wed, then 9/2 is Sunday.\n" +
                "To test, here is Java's display of Sep 2, 1945: " + LocalDate.parse("1945-09-02").toString() + " " + LocalDate.parse("1945-09-02").getDayOfWeek()
        );
    }

    private static void printMemoryItems() {
        System.out.println("\nSun=0, Mon=1, Tue=2, Wed=3, Thu=4, Fri=5, Sat=6");
        System.out.println("\n1600, 2000, 2400...: Tuesday = 2\n" +
                "1700, 2100, 2500...: Sunday = 0\n" +
                "1800, 2200, 2600...: Friday = 5\n" +
                "1900, 2300, 2700...: Wednesday = 3\n+");
        System.out.println("<year> / 12 +\n<year> % 12 +\n(<year> % 12) / 4\n");
        System.out.println("1/3  2/28(29)  3/14  4/4  5/9  6/6  7/11  8/8  9/5  10/10  11/7  12/12");
        System.out.println("( <end of Feb> <pi> <even months = m/d> <work 9 to 5> <go to 7-11> )");
    }
}
