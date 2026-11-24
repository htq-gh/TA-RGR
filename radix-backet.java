import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main() {
        Scanner scan = new Scanner(System.in);
        int size;
        int range;

        System.out.println("ПОРОЗРЯДЕ СОРТУВАНН НА ОСНОВІ СПИСКІВ\n");

        do {
            System.out.print("Вкажіть розмір масиву: ");
            if ((size = scan.nextInt()) <= 0)
                System.out.println("Масив не може бути порожнім або мати від'ємну розмірність!\n"
                        + "Повторіть спробу.");
        } while (size <= 0);

        do {
            System.out.print("\nВкажіть діапазон значень: ");
            if ((range = scan.nextInt()) <= 0)
                System.out.println("Масив має містити додатні значення!\n"
                        + "Повторіть спробу.");
        } while (range <= 0);

        int[] array = createArray(size, range);
        System.out.println("\nОтриманий масив: " + Arrays.toString(array));

        int maxValue = maxValue(array);
        System.out.println("Найбільше значення: " + maxValue);

        int factor = factor(maxValue);
        System.out.println("Кількість розрядів: " + factor);

        radix(array, 1, factor);
        System.out.println("\nВідсортований масив: " + Arrays.toString(array));
    }

    //Фунція для створення масиву за вказаними значеннями
    static int[] createArray(int size, int range) {
        int[] array = new int[size];
        Random random = new Random();

        //Заповнення масиву випадковими значеннями
        for(int i = 0; i < size; i++)
            array[i] = random.nextInt(range);

        return array;
    }

    static int maxValue(int[] array) {
        int maxValue = array[0];

        //Знаходження максимального значення лінійним пошуком, O(n)
        for(int i = 0; i < array.length; i++)
            if(array[i] > maxValue && array[i] != maxValue)
                maxValue = array[i];

        return maxValue;
    }

    static int factor(int value) {
        int k = 0;
        for(; value > 0; k++, value /= 10); //Визначення розрядності максимального значення, d
        return k;
    }

    static void radix(int[] array, int exp, int factor) {
        if (exp > Math.pow(10, factor))
            return;

        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int digit = 0; digit <= 9; digit++)
            buckets[digit] = new ArrayList<>();

        for (int value : array) {
            int digit = (value / exp) % 10;
            buckets[digit].add(value);
        }

        int k = 0;
        for (int digit = 0; digit <= 9; digit++)
            for (int value : buckets[digit])
                array[k++] = value;

        radix(array, exp * 10, factor); //Рекурсивний виклик функції
    }
}
