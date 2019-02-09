import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        // For now, return a List that's correct size, but contains only 0s
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            while (!s.empty() && k != 0 && s.peek() > A[i]) {
                s.pop();
                k--;
            }
            if (s.size() < A.length - k) {
                s.push(A[i]);
            }
        }

        return s;
    }


    public static boolean isPalindrome(Node n) {
        int size = 0;
        int mididx = 0;
        Node current = n;
        Node tempN = n;
        if (n == null)
            return true;

        while (tempN != null) {
            size = size + 1;
            tempN = tempN.next;
        }
        System.out.println(size);

        if (size == 1)
            return true;

        if (size % 2 == 0) {
            // even
            mididx = size / 2;
        } else {
            mididx = size / 2 + 1;
        }

        for (int i = 0; i < mididx; i++)
            current = current.next;


        Node temp = null;
        Node prev = null;

        for (int x = 0; x < size / 2; x++) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }


        current = prev;

        while (current != null) {
            //System.out.println(current.val);
            //System.out.print(n.val);
            System.out.println(n.val);
            if (current.val != n.val) {
                return false;
            } else {
                current = current.next;
                n = n.next;

            }


        }

        System.out.println("test");
        // move the pointer to the end
        return true;
    }

    public static String infixToPostfix(String s) {
        Stack<String> operator = new Stack<>();
        String delims = "[ ]+";
        String[] tokens = s.split(delims);
        String parenl = "(";
        String parenr = ")";
        String mul = "*";
        String min = "-";
        String plus = "+";
        String div = "/";
        String result = "";

        for (int x = 0; x < tokens.length; x++) {
            boolean isequalmul = mul.equals(tokens[x]);
            boolean isequalmin = min.equals(tokens[x]);
            boolean isequalplus = plus.equals(tokens[x]);
            boolean isequaldiv = div.equals(tokens[x]);
            boolean isequalpl = parenl.equals(tokens[x]);
            boolean isequalpr = parenr.equals(tokens[x]);

            if (isequalmul == true || isequalmin == true || isequalplus == true || isequaldiv == true) {
                operator.push(tokens[x]);
                continue;
            }

            if (isequalpr == true) {
                result = result + " " + operator.peek();
                operator.pop();
                continue;
            }

            if (isequalpl == true) {
                continue;
            }

            else {
                result= result + " " + tokens[x];
            }

            System.out.println(result);
        }
        result = result.trim();
        return result;
    }
}
