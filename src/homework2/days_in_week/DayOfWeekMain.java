package homework2.days_in_week;

public class DayOfWeekMain {

    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));

        /*
        System.out.println(getWorkingHours(DayOfWeek.TUESDAY));
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
        System.out.println(getWorkingHours(DayOfWeek.THURSDAY));
        System.out.println(getWorkingHours(DayOfWeek.FRIDAY));
        System.out.println(getWorkingHours(DayOfWeek.SATURDAY));
        System.out.println(getWorkingHours(DayOfWeek.SUNDAY));
        //*/
    }

    public static String getWorkingHours(DayOfWeek dayOfWeek) {
        String result;
        int hours = 0;

        for (int i = dayOfWeek.ordinal(); i < DayOfWeek.values().length; i++) {
            hours += DayOfWeek.values()[i].getWorkingHours();
        }

        if (hours == 0) {
            result = "Сегодня выходной";
        } else {
            result = "Кол-во оставшихся рабочих часов до конца недели: " + hours;
        }

        return result;
    }
}
