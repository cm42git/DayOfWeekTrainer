public enum DoomsdayOfMonth {
    JANUARY(3),
    FEBRUARY(28),
    MARCH(14),
    APRIL(4),
    MAY(9),
    JUNE(6),
    JULY(11),
    AUGUST(8),
    SEPTEMBER(5),
    OCTOBER(10),
    NOVEMBER(7),
    DECEMBER(12);

    private final int dayNum;

    DoomsdayOfMonth(int dayNum) {
        this.dayNum = dayNum;
    }

    public int doomsday() {
        return dayNum;
    }
}
