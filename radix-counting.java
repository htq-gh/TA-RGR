import java.util.Arrays;

public class Main {
    public static void main () {
        int[] array = new int[] { 420, 9, 1042, 53, 715, 100, 29, 999, 14 };

        System.out.println(Arrays.toString(array));
        //radixSort(array);
        //System.out.println(Arrays.toString(array));

        //mergeSort(array);
        //System.out.println(Arrays.toString(array));

        quickSort(array);
        System.out.println(Arrays.toString(array));

    }

    static int maxValue(int[] array) {
        int maxVal = array[0];
        for (int i : array)
            if (i > maxVal && i != maxVal)
                maxVal = i;
        return maxVal;
    }

    static void countingSortMod(int[] in, int exp) {
        int[] local = new int[10];

        for (int i : in)
            local[(i / exp) % 10]++;

        int sum = 0;
        for (int i = 0; i < local.length; i++) {
            sum += local[i];
            local[i] = sum;
        }

        int[] result = new int[in.length];
        for (int i = in.length - 1; i >= 0; i--) {
            int current = in[i];
            int digit = (current / exp) % 10;
            local[digit]--;
            result[local[digit]] = current;
        }

        for (int i = 0; i < in.length; i++)
            in[i] = result[i];
    }

    static void radixSort(int[] in) {
        for (int exp = 1; maxValue(in) / exp > 0; exp *= 10)
            countingSortMod(in, exp);
    }

    static void mergeSort(int[] array) {
        if (array.length <= 1)
            return;

        int midIndex = array.length / 2;
        int[] left = new int[midIndex];
        int[] right = new int[array.length - midIndex];
        for (int i = 0; i < midIndex; i++)
            left[i] = array[i];

        for (int i = midIndex; i < array.length; i++)
            right[i - midIndex] = array[i];

        mergeSort(left);
        mergeSort(right);
        mergeSub(array, left, right);
    }

    static void mergeSub(int[] array, int[] left, int[] right) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (left[i] < right[j])
                array[k++] = left[i++];
            else
                array[k++] = right[j++];
        }

        while (i < leftSize)
            array[k++] = left[i++];

        while (j < rightSize)
            array[k++] = right[j++];
    }

    static void quickSort(int[] array) {
        if (array.length < 2)
            return;
        quickSortSub(array, 0, array.length - 1);
    }

    static void quickSortSub(int[] array, int left, int right) {
        if (left < right) {
            int q = partition(array, left, right);
            quickSortSub(array, left, q);
            quickSortSub(array, q + 1, right);
        }
    }

    static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left - 1;
        int j = right + 1;
        while (true) {
            i++;
            while (array[i] < pivot)
                i++;

            j--;

            while (array[j] > pivot)
                j--;

            if (i >= j)
                return j;

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
