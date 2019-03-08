public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
            // update the height of the tree using the height of the left and right child
            // return balance(n)
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
            // update the height of the tree using the height of the left and right child
            // return balance(n)
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n==null) {
            return -1;
        }
        else {
            return n.height;
//            return Math.max(n.leftChild.height, n.rightChild.height)  + 1;
        }
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        // get the balance factor
        int bfn = balanceFactor(n);
        int rbf = balanceFactor(n.rightChild);
        int lbf = balanceFactor(n.leftChild);
        // if the balance factor is greater than 1 --> it is left heavy
        if (bfn > 1) {
            if (rbf < 0) {
                n.rightChild = rotateRight(n.rightChild);
            }
            n =rotateLeft(n);


            // check the balance factor of left child if it is g.e.t 1
            // rotate right
            // bf (left) == -1
            // left(child) and right(child)

        }
        else if (bfn <-1) {
            // if the balance factor is greater than -1 --> it is right heavy
            // check the balance factor of right child
            // if it is +1 > rotate left
            // if it is -1 --> rotate right and then left
            if (lbf > 0) {
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }

        return n;
        // if the balance factor is -1, 0, 1 return
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {

        System.out.println(height(n.rightChild));

        System.out.println(height(n.leftChild));

        System.out.println(height(n.rightChild) - height(n.leftChild));

        return (height(n.rightChild) - height(n.leftChild));
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        // create a new "root"
        TreeNode<T> templeft = n.leftChild;

        // swap the left child of the node with the right child of the new node
        n.leftChild = templeft.rightChild;

        // swap the original node with the right child of the newly created node
        templeft.rightChild = n;

        // update the height of the node
        n.height = Math.max(n.leftChild.height, n.rightChild.height)  + 1;

        // update the height of the child node
        templeft.height = Math.max(templeft.leftChild.height,templeft.rightChild.height) + 1;

        return templeft;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        // create a new "root"
        TreeNode<T> tempright = n.rightChild;

        // swap the left child of the node with the right child of the new node
        n.leftChild = tempright.leftChild;

        // swap the original node with the right child of the newly created node
        tempright.leftChild = n;

        // update the height of the node
        n.height = Math.max(n.leftChild.height, n.rightChild.height)  + 1;

        // update the height of the child node
        tempright.height = Math.max(tempright.leftChild.height,tempright.rightChild.height) + 1;

        return tempright;


    }
}
