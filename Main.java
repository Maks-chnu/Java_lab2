package Java_lab2;

public class Main {
    public static void main(String[] args) {
        // Чотири обов'язкові конструктори
        Time t1 = new Time(10, 30, 45);     // числами
        Time t2 = new Time("08:15:20");     // рядком
        Time t3 = new Time(3661);           // секундами
        Time t4 = new Time(t1);             // іншим часом

        System.out.println("=== Конструктори ===");
        System.out.println("t1 (числами):   " + t1);
        System.out.println("t2 (рядком):    " + t2);
        System.out.println("t3 (секундами): " + t3);
        System.out.println("t4 (копія t1):  " + t4);
        System.out.println("Без параметрів: " + new Time());

        System.out.println("\n=== Переведення ===");
        System.out.println(t1 + " = " + t1.toSeconds() + " с");
        System.out.println(t1 + " = " + t1.toMinutes() + " хв (з округленням)");
        System.out.println(t2 + " = " + t2.toMinutes() + " хв (з округленням)");
        System.out.println("00:00:30 = " + new Time(0, 0, 30).toMinutes() + " хв (30 с округлюється вгору)");

        System.out.println("\n=== Різниця ===");
        System.out.println(t1 + " - " + t2 + " = " + t1.differenceInSeconds(t2) + " с");
        System.out.println(t2 + " - " + t1 + " = " + t2.differenceInSeconds(t1) + " с");

        System.out.println("\n=== Додавання та віднімання секунд ===");
        System.out.println(t1 + " + 5000 с  = " + t1.addSeconds(5000));
        System.out.println(t1 + " - 40000 с = " + t1.subtractSeconds(40000));
        System.out.println("23:59:50 + 15 с = " + new Time(23, 59, 50).addSeconds(15));
        System.out.println("t1 після операцій не змінився: " + t1);

        System.out.println("\n=== Порівняння ===");
        System.out.println("t1.compareTo(t2) = " + t1.compareTo(t2));
        System.out.println("t1 пізніше t2? " + t1.isAfter(t2));
        System.out.println("t1 раніше t2?  " + t1.isBefore(t2));
        System.out.println("t1.equals(t4)? " + t1.equals(t4));
        System.out.println("t1 == t4?      " + (t1 == t4));

        System.out.println("\n=== Некоректні дані ===");
        String[] badInputs = {"25:00:00", "10:61:00", "abc", "10:30"};
        for (String input : badInputs) {
            try {
                new Time(input);
            } catch (IllegalArgumentException e) {
                System.out.println("\"" + input + "\" -> помилка: " + e.getMessage());
            }
        }
    }
}

