import java.util.Arrays;

class App {

    public static void main(String[] args) {

        /*
        BinaryTree tree = new BinaryTree();

        tree.add(100,1337);
        //System.out.println(tree.root.val);
        tree.root.add(11,9);
        tree.root.add(12,90);
        tree.root.add(9,3);
        tree.root.add(102,345);
        //System.out.println(tree.root.val);

        Integer[] highlight = {9};
        tree.print(highlight);

        System.out.println(tree.lookup(100));
        System.out.println(tree.lookup(101));
        System.out.println(tree.lookup(12));
        System.out.println(tree.lookup(222));
        System.out.println(tree.lookup(102));
        */

        

        
        Integer[] arr = Benchmark.generateArr(15);
        Integer[] arr_shuffled = Arrays.copyOf(arr, arr.length);
        Benchmark.shuffleArr(arr_shuffled);
        Integer[] arr_search = Benchmark.generateArr(1000);

        BinaryTree tree = new BinaryTree();
        BinaryTree tree_shuffled = new BinaryTree();
        for (int ii = 0; ii < arr.length; ii++) {
            tree.add(arr[ii], ii);
            tree_shuffled.add(arr_shuffled[ii], ii);
        }

        //tree.print();
        tree_shuffled.print();

        for (Integer k : tree_shuffled) {
            System.out.println(k);
        }
        

        //int[] sizes = {10,20,40,80,160,320,640,1280,2560,5120,10240,20480,40960,81920};
        //Benchmark.one(sizes, 10000);

        /*int[] t = {1,2,3};
        int[] tt = Arrays.copyOf(t, t.length);
        System.out.println(Arrays.toString(t));
        System.out.println(Arrays.toString(tt));
        Integer[] arr = Benchmark.generateArr(20);
        Benchmark.shuffleArr(arr);
        BinaryTree tree_shuffled = new BinaryTree();
        for (int ii = 0; ii < arr.length; ii++) {
            tree_shuffled.add(arr[ii], ii);
        }
        tree_shuffled.print();*/

    }
    
}
