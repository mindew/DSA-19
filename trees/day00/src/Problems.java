import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<>();
        Collections.sort(values);

        BST = BSTrecurse(BST, values, 0, values.size(), values.size());

        return BST;

    }


    public static BinarySearchTree BSTrecurse(BinarySearchTree<Integer> BST, List<Integer> values, int lo, int hi, int length) {
        int mid = (lo+hi)/2;
        // base case: if the "mid" is 0 or length, stop recursing and return BST
        if (hi<=lo) {
            return BST;
        } else {
            BST.add(values.get(mid));
            BSTrecurse(BST, values, lo, mid, mid-lo);
            BSTrecurse(BST, values, mid+1, hi, hi-mid);
        }

        return BST;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
