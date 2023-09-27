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

        
        for (int iii = 0; iii < 1; iii ++) {
        
        Integer[] arr = Benchmark.generateArr(1000);
        Integer[] arr_shuffled = Arrays.copyOf(arr, arr.length);
        Benchmark.shuffleArr(arr_shuffled);
        //Integer[] arr_search = Benchmark.generateArr(1000);

        //BinaryTree tree = new BinaryTree();
        BinaryTree tree_shuffled = new BinaryTree();
        for (int ii = 0; ii < arr.length; ii++) {
        //    tree.add(arr[ii], ii);
            //tree_shuffled.add(arr_shuffled[ii], arr_shuffled[ii]);
        }
        
        Integer[] tmp = {21,12,35,7,1,3,9,15,13,17,20,25,28,27,31};
        for (int ii = 0; ii < tmp.length; ii++) {
            //tree_shuffled.add(tmp[ii], tmp[ii]);
        }
 
        
        tree_shuffled.add(20,20);
        tree_shuffled.add(1,1);
        tree_shuffled.add(10,10);
        tree_shuffled.add(11,11);
        tree_shuffled.add(4,4);
        tree_shuffled.add(2,2);
        tree_shuffled.add(9,9);
        tree_shuffled.add(8,8);
        tree_shuffled.add(7,7);
        tree_shuffled.add(21,21);
        tree_shuffled.add(40,40);
        tree_shuffled.add(30,30);
        tree_shuffled.add(42,42);
        tree_shuffled.add(43,43);
        tree_shuffled.add(44,44);
        tree_shuffled.add(31,31);
        tree_shuffled.add(32,32);
        tree_shuffled.add(28,28);

        //tree.print();
        //tree_shuffled.print();

        int total = 0;
        for (Integer k : tree_shuffled) {
            System.out.println("FOUND: " + k);
            total++;
            if (total == 10000){
                System.out.println("ERROR");
                break;
            }
        }
        System.out.println("Total: " + total);

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
