package Java_lab2;

public class Circle {

    // Спеціальне значення: кола збігаються, точок перетину нескінченно багато
    public static final int INFINITE_INTERSECTIONS = -1;

    // Допустима похибка при порівнянні дробових чисел
    private static final double EPS = 1e-9;

    private final double x;      
    private final double y;       
    private final double radius;  


    public Circle(double x, double y, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радіус має бути більшим за 0, отримано: " + radius);
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(double radius) {
        this(0, 0, radius);
    }

    // Копія іншого кола
    public Circle(Circle other) {
        this(other.x, other.y, other.radius);
    }

    // Довжина кола: L = 2πr
    public double getLength() {
        return 2 * Math.PI * radius;
    }

    // Площа круга: S = πr²
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Чи належить точка (px, py) кругу (включно з точками на самому колі)
    public boolean containsPoint(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        return dx * dx + dy * dy <= radius * radius + EPS;
    }

    // Відстань між центрами двох кіл
    public double distanceTo(Circle other) {
        return Math.hypot(other.x - this.x, other.y - this.y);
    }

    // Кількість точок перетину з іншим колом: 0, 1, 2 або INFINITE_INTERSECTIONS
    public int countIntersections(Circle other) {
        double d = distanceTo(other);
        double sumR = this.radius + other.radius;
        double diffR = Math.abs(this.radius - other.radius);

        // Кола збігаються
        if (d < EPS && diffR < EPS) {
            return INFINITE_INTERSECTIONS;
        }
        // Далеко одне від одного або одне всередині іншого
        if (d > sumR + EPS || d < diffR - EPS) {
            return 0;
        }
        // Зовнішній або внутрішній дотик
        if (Math.abs(d - sumR) < EPS || Math.abs(d - diffR) < EPS) {
            return 1;
        }
        // Звичайний перетин
        return 2;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Коло[центр=(%.2f; %.2f), r=%.2f]", x, y, radius);
    }
}