import java.time.LocalDate;

public abstract class DayOfWeekAlgorithm {
    //    public static final int[] centuryLookup = new int[]{2, 0, 5, 3};
//    public static final int[] doomsdayOfMonth = new int[]{3, 28, 14, 4, 9, 6, 11, 8, 5, 10, 7, 12};
    //    public static final String[] dayOfWeek = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public static int centuryNum;
    public static int century;
    public static int divideBy12;
    public static int remainderBy12;
    public static int divideRemainderBy4;
    public static int finalSumUser;
    public static int doomsdayDayUser;
    public static int finalDOWUser;

    public static int centuryCalc(LocalDate date) {
        int century = date.getYear() / 100;
        return (Math.floorMod((1 - century), 4) * 2) - ((century / 2) % 2);
    }

    public static int realFinalSum(LocalDate dateToParse) {
        int year = dateToParse.getYear();
//        int centuryNumberForSum = centuryLookup[year / 100 % 4];
        int centuryNumberForSum = centuryCalc(dateToParse);
        int yearPart = year % 100;
        int divBy12ForSum = yearPart / 12;
        int rem12ForSum = yearPart % 12;
        int divBy4ForSum = rem12ForSum / 4;
        return centuryNumberForSum + divBy12ForSum + rem12ForSum + divBy4ForSum;
    }

//    public static int realCenturyPart(LocalDate date) {
//        return centuryLookup[date.getYear() / 100 % 4];
//    }

    public static int realDivBy12(LocalDate date) {
        return (date.getYear() % 100) / 12;
    }

    public static int realRemBy12(LocalDate date) {
        return (date.getYear() % 100) % 12;
    }

    public static int realDivBy4(LocalDate date) {
        return realRemBy12(date) / 4;
    }

}
