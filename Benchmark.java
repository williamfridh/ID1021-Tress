import java.util.Arrays;
import java.util.Random;

class Benchmark {

    /**
     * Benchmark the time to search for each key in a sorted and unsorted tree.
     * 
     * @param sizes to try.
     * @param tries to get the best time.
     */
    static void one(int[] sizes, int tries) {
        System.gc();

        System.out.println("Size\t\tSorted(us)\t\tSorted(diff.)\t\tUnsorted(us)\t\tUnsorted(diff.)");

        double last_best_sorted = 1;
        double last_best_shuffled = 1;

        for (int size : sizes) {

            double best_sorted = Double.MAX_VALUE;
            double best_shuffled = Double.MAX_VALUE;

            for (int i = 0; i < tries; i++) {

                Integer[] arr = Benchmark.generateArr(size);
                Integer[] arr_shuffled = Arrays.copyOf(arr, arr.length);
                Benchmark.shuffleArr(arr_shuffled);
                Integer[] arr_search = Arrays.copyOf(arr, arr.length);
                Benchmark.shuffleArr(arr_search);

                BinaryTree tree = new BinaryTree();
                BinaryTree tree_shuffled = new BinaryTree();
                for (int ii = 0; ii < arr.length; ii++) {
                    tree.add(arr[ii], ii);
                    tree_shuffled.add(arr_shuffled[ii], ii);
                }

                // Warm up
                for (Integer key : arr_search) {
                    tree.lookup(key);
                    tree_shuffled.lookup(key);
                }

                double n0 = System.nanoTime();
                for (Integer key : arr_search)
                    tree.lookup(key);
                double n1 = System.nanoTime();
                double n = n1 - n0;
                if (n < best_sorted)
                    best_sorted = n;
                
                n0 = System.nanoTime();
                for (Integer key : arr_search)
                    tree_shuffled.lookup(key);
                n1 = System.nanoTime();
                n = n1 - n0;
                if (n < best_shuffled)
                    best_shuffled = n;

            }

            System.out.printf("%d\t&\t%.0f\t&\t\t%.2f\t&\t\t%.0f\t&\t\t%.2f\n", size, best_sorted, best_sorted/last_best_sorted, best_shuffled, best_shuffled/last_best_shuffled);

            last_best_sorted = best_sorted;
            last_best_shuffled = best_shuffled;

        }
    }



    static Integer[] generateArr(int size) {
        Random rn = new Random();
        int last_ins = 0;
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            last_ins += rn.nextInt(3) + 1;
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

