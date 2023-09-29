import java.util.Iterator;



class BinaryTree implements Iterable<Integer> {

    Node root;



    public BinaryTree() {
        root = null;
    }



    /**
     * Prints out the tree in a tree like structure. Used for debugging.
     * 
     * Note that this method could be upgraded to display the branchews
     * clearer as vertical lines are missing leadning to gaps.
     */
    public void print() {
        print(new Integer[0]);
    }
    public void print(Integer[] highlight) {
        if (root == null) {
            System.out.println("Empty tree.");
        }
        print("", root, highlight);
    }
    public void print(String d, Node n, Integer[] highlight) {
        System.out.print(d);
        if (n != root)
            System.out.print("└───");
        System.out.print("[" + n.key + ":" + n.val + "]");
        for (Integer comp : highlight)
            if (comp == n.key)
                System.out.print("██");
        System.out.println();
        if (!n.isLeaf()) {
            if (n.left != null) {
                if (n.right != null) {
                    print(d + "    ", n.left, highlight);
                } else {
                    print(d + "    ", n.left, highlight);
                }
            }
            if (n.right != null)
                print(d + "    ", n.right, highlight);
        }
    }



    public class Node {

        Node left, right; // Smaller and larger respectivly.
        Integer key, val;



        Node(int k, int v) {
            left = null;
            right = null;
            key = k;
            val = v;
        }



        /**
         * A simple helper method only used to shorten if statements.
         */
        boolean isLeaf() {
            return (left == null && right == null);
        }



        void add(Integer k, Integer v) {
            if (key.compareTo(k) > 0) {
                if (left == null) {
                    left = new Node(k, v);
                } else {
                    left.add(k, v);
                }
            } else if (key.compareTo(k) < 0) {
                if (right == null) {
                    right = new Node(k, v);
                } else {
                    right.add(k, v);
                }
            } else {
                val = v;
            }
        }

    }



    /**
     * Used for building even trees. This helps with benchmarking.
     * @param arr to build the array out of (must be ordered).
     */
    public void buildEven(Integer[] arr) {
        add(arr[arr.length / 2]);

        int len2 = arr.length / 2;
        int len3 = len2;
        if (arr.length % 2 == 0)
            len3--;

        Integer[] arr2 = new Integer[len2];
        Integer[] arr3 = new Integer[len3];

        for (int ii = 0; ii < arr2.length; ii++)
            arr2[ii] = arr[ii];

        for (int ii = 0; ii < arr3.length; ii++)
            arr3[ii] = arr[arr.length / 2 + ii + 1];

        if (arr2.length > 0)
            buildEven(arr2);
            
        if (arr3.length > 0)
            buildEven(arr3);

    }



    public void add(Integer i) {
        add(i, i);
    }
    public void add(Integer k, Integer v) {
        if (root == null) {
            root = new Node(k, v);
        } else {
            root.add(k, v);
        }
    }



    public Integer lookup(Integer k) {
        Node tar = root;
        while (tar != null) {
            if (tar.key.compareTo(k) > 0) {
                tar = tar.left;
            } else if (tar.key.compareTo(k) < 0) {
                tar = tar.right;
            } else {
                return tar.val;
            }  
        }
        System.out.println("NOT FOUND");
        return null;
    }



    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }



    private class TreeIterator implements Iterator<Integer> {
    
        private Node next;
        private Stack<Node> stack;
    
        TreeIterator() {
            stack = new Stack<Node>();
            Node tmp = root;
            while (tmp.left != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            next = tmp;
        }



        @Override
        public boolean hasNext() {
            goToNext();
            return (next != null);
        }



        @Override
        public Integer next() {
            return next.val;
        }



        /**
         * Go to the next element in the stack (ascending order).
         */
        private void goToNext() {

            if (next.right != null) {
                next = next.right;
                while(next.left != null) {
                    stack.push(next);
                    next = next.left;
                }
                return;
            }

            if (next.isLeaf()) {
                next = stack.pop();
                return;
            }

            if (next.left != null)
                next = stack.pop();

        }



        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    

}

