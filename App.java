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

        

        /*
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

        tree.print();
        tree_shuffled.print();
        */

        int[] sizes = {2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};
        Benchmark.one(sizes, 20000);

    }
    
}
