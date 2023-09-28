import java.util.Iterator;



class BinaryTree implements Iterable<Integer> {

    Node root;



    /**
     * Constuctor.
     */
    public BinaryTree() {
        root = null;
    }



    /**
     * Print.
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



    /**
     * Node class.
     * 
     * Left = smaller
     * Right = larger
     */
    public class Node {

        Node left, right;
        Integer key, val;

        Node(int k, int v) {
            left = null;
            right = null;
            key = k;
            val = v;
        }

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
            } else {
                if (right == null) {
                    right = new Node(k, v);
                } else {
                    right.add(k, v);
                }
            }
            
            if (key == k) {
                val = v;
            }
        }

    }



    /**
     * Add node.
     */
    public void add(Integer k, Integer v) {
        if (root == null) {
            root = new Node(k, v);
            return;
        } else {
            root.add(k, v);
        }
    }



    /**
     * Lookup.
     */
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



    /**
     * Initilize the iterator.
     */
    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }



    private class TreeIterator implements Iterator<Integer> {
    
        private Node next;
        private Stack<Node> stack;
        private boolean just_popped = false;
        private boolean passed_root = false;
    
        TreeIterator() {
            stack = new Stack<Node>();

            // Go far left to start there.
            Node tmp = root;
            while (tmp.left != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            next = tmp;
        }

        @Override
        public boolean hasNext() {
            return (next != null);
        }

        @Override
        public Integer next() {
            if (next == null)
                return null;
            Integer tmp = next.val;
            goToNext();
            return tmp;
        }

        /**
         * Go to the next element in the stack (ascending order).
         * 
         * The logic in this function can probably be simplified trough removal of the variable "just_popped".
         * 
         * DO NOT TOUCH THIS. Feeling after completion = (X_X)
         */
        private void goToNext() {

            if (next == root && root.left == null) {
                if (passed_root) {
                    next = null;
                    return;
                }
                passed_root = true;
            }
                
            while (next.right != null && (next.left == null || just_popped)) {

                next = next.right;
                just_popped = false;
                if (next.isLeaf() || (next.key >= root.key && next.left == null) || (next.key < root.key && next.left == null))
                    return;
            }

            if (next.left != null && !just_popped) {
                while (next.left != null) {
                    stack.push(next);
                    next = next.left;
                }
                return;
            }

            if (next != null && next.isLeaf()) {
                next = stack.pop();
                just_popped = true;
                return;
            }

            if (just_popped) {
                next = stack.pop();
                return;
            }

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    

}

