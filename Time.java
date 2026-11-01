package Java_lab2;

public class Time implements Comparable<Time> {

    private static final int SECONDS_IN_MINUTE = 60;
    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_DAY = 86400;

    private final short hours;
    private final short minutes;
    private final short seconds;

    public Time() {
        this(0, 0, 0);
    }

    //Ініціалізація числами: new Time(10, 30, 45)
    public Time(int hours, int minutes, int seconds) {
        validate(hours, minutes, seconds);
        this.hours = (short) hours;
        this.minutes = (short) minutes;
        this.seconds = (short) seconds;
    }

    //Ініціалізація рядком: new Time("10:30:45")
    public Time(String text) {
        this(parsePart(text, 0), parsePart(text, 1), parsePart(text, 2));
    }

    //Ініціалізація секундами: new Time(3661) -> 01:01:01
    //    Значення поза межами доби "загортаються": 90000 -> 01:00:00, -1 -> 23:59:59
    public Time(int totalSeconds) {
        this(normalize(totalSeconds) / SECONDS_IN_HOUR,
             normalize(totalSeconds) % SECONDS_IN_HOUR / SECONDS_IN_MINUTE,
             normalize(totalSeconds) % SECONDS_IN_MINUTE);
    }

    //Ініціалізація іншим часом (конструктор копіювання)
    public Time(Time other) {
        this(other.hours, other.minutes, other.seconds);
    }


    // Переведення в секунди від початку доби
    public int toSeconds() {
        return hours * SECONDS_IN_HOUR + minutes * SECONDS_IN_MINUTE + seconds;
    }

    // Переведення в хвилини з округленням до цілої хвилини (30 с і більше -> вгору)
    public int toMinutes() {
        return (int) Math.round(toSeconds() / 60.0);
    }

    // Різниця між двома моментами часу в секундах (this - other).
    // Додатна, якщо this пізніше за other.
    public int differenceInSeconds(Time other) {
        return this.toSeconds() - other.toSeconds();
    }

    // Додавання заданої кількості секунд (повертає новий об'єкт)
    public Time addSeconds(int secondsToAdd) {
        return new Time(normalize((long) toSeconds() + secondsToAdd));
    }

    // Віднімання заданої кількості секунд (повертає новий об'єкт)
    public Time subtractSeconds(int secondsToSubtract) {
        return new Time(normalize((long) toSeconds() - secondsToSubtract));
    }

    // Порівняння: від'ємне — this раніше, 0 — рівні, додатне — this пізніше
    @Override
    public int compareTo(Time other) {
        return Integer.compare(this.toSeconds(), other.toSeconds());
    }

    public boolean isBefore(Time other) {
        return compareTo(other) < 0;
    }

    public boolean isAfter(Time other) {
        return compareTo(other) > 0;
    }


    public short getHours() {
        return hours;
    }

    public short getMinutes() {
        return minutes;
    }

    public short getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Time)) return false;
        Time other = (Time) obj;
        return hours == other.hours && minutes == other.minutes && seconds == other.seconds;
    }

    @Override
    public int hashCode() {
        return toSeconds();
    }

    private static void validate(int h, int m, int s) {
        if (h < 0 || h > 23) {
            throw new IllegalArgumentException("Години мають бути від 0 до 23, отримано: " + h);
        }
        if (m < 0 || m > 59) {
            throw new IllegalArgumentException("Хвилини мають бути від 0 до 59, отримано: " + m);
        }
        if (s < 0 || s > 59) {
            throw new IllegalArgumentException("Секунди мають бути від 0 до 59, отримано: " + s);
        }
    }

    // Розбирає рядок "гг:хх:сс" і повертає частину з номером index
    private static int parsePart(String text, int index) {
        if (text == null) {
            throw new IllegalArgumentException("Рядок часу не може бути null");
        }
        String[] parts = text.trim().split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Очікується формат гг:хх:сс, отримано: \"" + text + "\"");
        }
        try {
            return Integer.parseInt(parts[index].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некоректне число в рядку \"" + text + "\"");
        }
    }

    // Приводить будь-яку кількість секунд до діапазону 0..86399 (загортання по добі)
    private static int normalize(long totalSeconds) {
        return (int) (((totalSeconds % SECONDS_IN_DAY) + SECONDS_IN_DAY) % SECONDS_IN_DAY);
    }
}