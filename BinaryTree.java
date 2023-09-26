class BinaryTree {

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
        print(0, root, highlight);
    }
    public void print(int d, Node n, Integer[] highlight) {
        for (int i = 0; i < d - 1; i++)
            System.out.print("    ");
        if (n != root && d > 0)
            System.out.print("    ");
        if (n != root)
            System.out.print("└───");
        System.out.print("[" + n.key + ":" + n.val + "]");
        for (Integer comp : highlight)
            if (comp == n.key)
                System.out.print("██");
        System.out.println();
        if (!n.isLeaf()) {
            if (n.left != null)
                print(d + 1, n.left, highlight);
            if (n.right != null)
                print(d + 1, n.right, highlight);
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
     * Return the largest key.
     */
    public Node getLargestKey() {
        Node res = root;
        while (res.right != null) {
            res = res.right;
        }
        return res;
    }

}

