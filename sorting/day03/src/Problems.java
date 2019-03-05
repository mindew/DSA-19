import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // count how many neg numbers are in an array
        int counter = 0;
        for (int i =0; i<A.length; i++) {
            if (A[i]<0) {
                counter++;
            }
        }

        // divide an array into positive and negative arrays
        int[] pos = new int[A.length-counter];
        int[] neg = new int[counter];

        // pointer for positive and negative array
        int p = 0;
        int n = 0;

        for (int i = 0; i<A.length; i++) {
            if (A[i] < 0) {
                neg[n] = -1*A[i];
                n++;
            }
            else {
                pos[p] = A[i];
                p++;
            }
        }

        // conduct counting sort on positive array and negated negative array(all pos)
        CountingSort.countingSort(pos);
        CountingSort.countingSort(neg);

        // reverse the negative array after multiplying it with negative
        int j = neg.length;
        int[] revneg = new int[j];
        for (int i = 0; i < n; i++) {
            revneg[j - 1] = -1*neg[i];
            j = j - 1;
        }

        // copy the negative array into A
        System.arraycopy(revneg,0,A,0,revneg.length);
        // then copy the positive array into A
        System.arraycopy(pos,0,A,revneg.length,pos.length);

    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // there are 27 characters in alphabet
        LinkedList<String>[] countingA = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            // create a linked list of a linked list
            countingA[i] = new LinkedList<>();
        }

        for (String s : A) {
            int CharN = getNthCharacter(s, n);
            countingA[CharN].add(s);
        }

        int h = 0; // index in A to place numbers

        for (LinkedList<String> list : countingA) {
            for (int x = 0; x < list.size(); x++) {
                A[h] = list.get(x);
                h++;
            }
        }

        // now we have sorted character number array....
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int i = 0; i<stringLength; i++) {
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
