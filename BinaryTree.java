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
            } if (key == k) {
                val = v;
            }
        }

        Node goLeft() {
            return left;
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
        private boolean go_back = false;
        private boolean passed_root = false;
        private boolean first_run = false;
    
        TreeIterator() {
            stack = new Stack<Node>();

            // Go far left to start there.
            Node tmp = root;
            while (tmp.left != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
            next = tmp;
            System.out.println("START KEY: " + tmp.key);
        }

        @Override
        public boolean hasNext() {
            return nextExists();
        }

        @Override
        public Integer next() {
            if (next == null)
                return null;
            return next.val;
        }

        private boolean nextExists() {
            
            if (next != root) {
                try {
                    next = stack.pop();
                } catch (Exception e) {
                    // Do nothing.
                }
            } else {
                if (passed_root)
                    return false;
                passed_root = true;
            }
                if (next.right == null) {
                    return true;
                } else {
                    while (next.right.left != null
                    && next.right != null) {
                        next = next.right;
                    }
                    while(next.left != null) {
                        stack.push(next);
                        next = next.left;
                    }
                    return true;
                }

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    

}

