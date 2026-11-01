package Java_lab2;

public class CircleMain {
    public static void main(String[] args) {
        Circle c1 = new Circle(0, 0, 5);

        System.out.println("=== Основне коло ===");
        System.out.println(c1);
        System.out.printf("Довжина кола: %.4f%n", c1.getLength());
        System.out.printf("Площа круга:  %.4f%n", c1.getArea());

        System.out.println("\n=== Належність точки кругу ===");
        double[][] points = {{1, 1}, {3, 4}, {6, 0}, {-2, -4}};
        for (double[] p : points) {
            System.out.printf("Точка (%.1f; %.1f): %s%n",
                    p[0], p[1], c1.containsPoint(p[0], p[1]) ? "належить" : "не належить");
        }

        System.out.println("\n=== Точки перетину з іншими колами ===");
        Circle[] others = {
                new Circle(8, 0, 3),    // зовнішній дотик
                new Circle(4, 0, 3),    // перетин
                new Circle(20, 0, 2),   // далеко
                new Circle(1, 0, 2),    // всередині
                new Circle(2, 0, 3),    // внутрішній дотик
                new Circle(c1)          // збігається
        };
        for (Circle other : others) {
            System.out.println(c1 + " і " + other + " -> " + describe(c1.countIntersections(other)));
        }

        System.out.println("\n=== Некоректний радіус ===");
        try {
            new Circle(1, 1, -3);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static String describe(int count) {
        if (count == Circle.INFINITE_INTERSECTIONS) {
            return "нескінченно багато точок (кола збігаються)";
        }
        return count + " точк(и) перетину";
    }
}