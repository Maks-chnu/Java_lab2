package Java_lab2;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task3 {

    private static final int MIN_VALUE = 0;     // діапазон випадкових чисел
    private static final int MAX_VALUE = 99;
    private static final int MAX_SIZE = 100;    // максимальний розмір масиву

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        // Введення розмірів масивів
        int n = readSize(in, "першого");
        int m = readSize(in, "другого");

        //Заповнення випадковими числами
        int[] a = generateArray(n, random);
        int[] b = generateArray(m, random);
        System.out.println("\nПерший масив (початковий):  " + Arrays.toString(a));
        System.out.println("Другий масив (початковий):  " + Arrays.toString(b));

        //Сортування бульбашкою
        bubbleSort(a);
        bubbleSort(b);
        System.out.println("\nПерший масив (відсортований): " + Arrays.toString(a));
        System.out.println("Другий масив (відсортований): " + Arrays.toString(b));

        //Злиття у третій масив
        int[] merged = merge(a, b);
        System.out.println("\nРезультат злиття: " + Arrays.toString(merged));

        in.close();
    }

    // Введення розміру масиву з перевіркою (1..MAX_SIZE)
    private static int readSize(Scanner in, String name) {
        System.out.print("Введіть розмір " + name + " масиву (1.." + MAX_SIZE + "): ");
        int size = in.nextInt();
        while (size < 1 || size > MAX_SIZE) {
            System.out.print("Помилка: розмір має бути від 1 до " + MAX_SIZE + ". Введіть ще раз: ");
            size = in.nextInt();
        }
        return size;
    }

    // Створює масив заданого розміру з випадковими цілими числами [MIN_VALUE; MAX_VALUE]
    private static int[] generateArray(int size, Random random) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        }
        return arr;
    }

    // Сортування бульбашкою за зростанням (змінює переданий масив)
    private static void bubbleSort(int[] arr) {
        for (int pass = 0; pass < arr.length - 1; pass++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - pass; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break; // обмінів не було — масив уже відсортований
            }
        }
    }

    // Злиття двох відсортованих масивів в один відсортований
    private static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0; // індекс у a
        int j = 0; // індекс у b
        int k = 0; // індекс у result

        // Поки в обох масивах є елементи — беремо менший
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        // Дописуємо залишок (виконається лише один з циклів)
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }
}