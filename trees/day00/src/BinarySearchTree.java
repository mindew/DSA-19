import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// runtime: O(nlogn) for everything

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        // create a new linked list to store the result
        List<T> traversed = new LinkedList<>();
        TreeNode<T> pointer = root;
        // find a minimum and move a pointer to the node with minimum key
        // only if there's any node...
        if (size > 0) {
            while (pointer.hasLeftChild() != false) {
                pointer = pointer.leftChild;
                //System.out.println("Node: " + pointer.key );
            }
            //System.out.println("Size is: " + size);

            // now we have minimum. find the successor from the minimum
            // add the key to the linked list while recursively finding successor
            // but first, add the minimum to the linked list
            if (traversed.size() == 0) {
                System.out.println(pointer);
                traversed.add(pointer.key);
            }

            // edgecase: T has 0 node therefore has nothing to traverse
            // System.
            if (findSuccessor(pointer) == null)
                return traversed;

            // elsewise, find the successor of the current node and add it to the link
            while (findSuccessor(pointer) != null) {
                System.out.println("Node: " + pointer.key);
                pointer = findSuccessor(pointer);
                traversed.add(pointer.key);
            }
        }
        return traversed;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its
     * * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;
        TreeNode<T> replacement;

        if (n.isLeaf()) {
            // Case 1: no children
            replacement = null;
        System.out.println("no child"); }
        else if (n.hasRightChild() != n.hasLeftChild()) {
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
            System.out.println("onechild"); }
        else {
            System.out.println("hit");
            // Case 3: two children
            // find inorder successor or predecessor of the node
            // copy contents of the inorder successor to the node
            // delete the inorder successor
            replacement = findPredecessor(n);
            delete(replacement);
            replacement.moveChildrenFrom(n);
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the predecessor TreeNode by calling the function you will implement below
            TreeNode<T> predecessor = findPredecessor(n);
            // return the key of predecessor TreeNode
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the successor TreeNode by calling the function you will implement below
            TreeNode<T> successor = findSuccessor(n);
            // return the key of successor TreeNode
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        TreeNode<T> pointer = n.parent;
        TreeNode<T> max = n.leftChild;

        // if given node has a left child --> return the maximum value of it's children
        if (n.leftChild!=null) {
            while (max.hasRightChild() != false) {
                max = max.rightChild;
                //System.out.println("Node: " + pointer.key );
            }

            return max;

        } else if (pointer == null) {
            return null;
        }

        while (pointer != null && n == pointer.leftChild) {
            n = pointer;
            pointer = pointer.parent;
        }

        return pointer;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        TreeNode<T> pointer = n.parent;
        TreeNode<T> min = n.rightChild;

        // if given node has a left child --> return the maximum value of it's children
        if (n.rightChild!=null) {
            while (min.hasLeftChild() != false) {
                min = min.leftChild;
                //System.out.println("Node: " + pointer.key );
            }

            return min;

        }

        while (pointer!= null && n == pointer.rightChild) {
            n = pointer;
            pointer = pointer.parent;
        }

        return pointer;
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
