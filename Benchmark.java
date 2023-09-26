import java.util.Arrays;
import java.util.Random;

class Benchmark {

    /**
     * Benchmark the time to search for each key in a sorted and unsorted tree.
     * 
     * @param sizes to try.
     * @param tries to get the best time.
     * 
Size            Sorted(us)              Sorted(diff.)           Sorted/Size             Unsorted(ns)            Unsorted(diff.)         Unsorted/Size
10      &       0.02    &               15.33   &               2       &               6.61    &               6.61    &               1
20      &       0.03    &               2.04    &               2       &               6.61    &               1.00    &               0
40      &       0.08    &               2.47    &               2       &               1.20    &               0.18    &               0
80      &       0.16    &               2.09    &               2       &               2.40    &               2.00    &               0
160     &       0.26    &               1.62    &               2       &               2.30    &               0.96    &               0
320     &       0.48    &               1.85    &               2       &               2.30    &               1.00    &               0
640     &       1.05    &               2.18    &               2       &               2.31    &               1.00    &               0
1280    &       2.03    &               1.93    &               2       &               2.30    &               1.00    &               0
2560    &       4.10    &               2.02    &               2       &               2.61    &               1.13    &               0
5120    &       8.90    &               2.17    &               2       &               3.01    &               1.15    &               0

Size            Sorted(us)              Sorted(diff.)           Sorted/Size             Unsorted(ns)            Unsorted(diff.)         Unsorted/Size
10      &       0.00    &               0.30    &               0       &               6.61    &               6.61    &               1
20      &       0.00    &               1.00    &               0       &               6.61    &               1.00    &               0
40      &       0.00    &               1.00    &               0       &               6.61    &               1.00    &               0
80      &       0.00    &               0.67    &               0       &               2.10    &               0.32    &               0
160     &       0.00    &               1.00    &               0       &               1.80    &               0.86    &               0
320     &       0.00    &               1.00    &               0       &               1.70    &               0.94    &               0
640     &       0.00    &               1.00    &               0       &               1.80    &               1.06    &               0
1280    &       0.00    &               1.00    &               0       &               1.81    &               1.01    &               0
2560    &       0.00    &               1.00    &               0       &               2.20    &               1.22    &               0
5120    &       0.00    &               1.00    &               0       &               3.30    &               1.50    &               0
10240   &       0.00    &               1.50    &               0       &               3.21    &               0.97    &               0

Above 10k >> Stackoverflow issues
     */
    static void one(int[] sizes, int tries) {

        System.out.println("Size\t\tSorted(us)\t\tSorted(diff.)\t\tSorted/Size\t\tUnsorted(ns)\t\tUnsorted(diff.)\t\tUnsorted/Size");

        double last_best_sorted = 1;
        double last_best_shuffled = 1;

        for (int size : sizes) {

            System.gc();

            double best_sorted = Double.MAX_VALUE;
            double best_shuffled = Double.MAX_VALUE;

            Integer[] arr = Benchmark.generateArr(size);
            Integer[] arr_shuffled = Arrays.copyOf(arr, arr.length);
            Integer[] arr_search = Arrays.copyOf(arr, 10);

            BinaryTree tree = new BinaryTree();
            for (int j = 0; j < arr.length; j++)
                tree.add(arr[j], j);

            for (int i = 0; i < tries; i++) {

                Benchmark.shuffleArr(arr_shuffled);
                Benchmark.shuffleArr(arr_search);

                BinaryTree tree_shuffled = new BinaryTree();
                for (int ii = 0; ii < arr.length; ii++) {
                    tree_shuffled.add(arr_shuffled[ii], ii);
                }

                // Warm up
                for (int ii = 10; ii > 0; ii--) {
                    //tree.lookup(arr[arr.length - 1]);
                    tree_shuffled.lookup(arr[arr.length - 1]);
                }

                double n0 = System.nanoTime();
                //for (int ii = 100; ii > 0; ii--)
                    //tree.lookup(arr[arr.length - 1]);
                double n1 = System.nanoTime();
                double n = (n1 - n0) / 100;
                if (n < best_sorted)
                    best_sorted = n;
                
                n0 = System.nanoTime();
                for (int ii = 100; ii > 0; ii--)
                    tree_shuffled.lookup(arr[arr.length - 1]);
                n1 = System.nanoTime();
                n = (n1 - n0) / 100;
                if (n < best_shuffled)
                    best_shuffled = n;

            }

            System.out.printf("%d\t&\t%.2f\t&\t\t%.2f\t&\t\t%.0f\t&\t\t%.2f\t&\t\t%.2f\t&\t\t%.0f\n", size, best_sorted/1000, best_sorted/last_best_sorted, best_sorted/size, best_shuffled, best_shuffled/last_best_shuffled, best_shuffled/size);

            last_best_sorted = best_sorted;
            last_best_shuffled = best_shuffled;

        }
    }



    static Integer[] generateArr(int size) {
        Random rn = new Random();
        int last_ins = 0;
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            last_ins += rn.nextInt(4) + 1;
            arr[i] = last_ins;
        }
        return arr;
    }



    static void shuffleArr(Integer[] arr) {
        Random rn = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            Integer t = arr[i];
            Integer rn_int = rn.nextInt(arr.length - 1);
            arr[i] = arr[rn_int];
            arr[rn_int] = t;
        }
    }
    
}

