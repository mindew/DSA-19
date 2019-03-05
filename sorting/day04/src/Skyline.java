import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Time complexity: O(n logn) & Space complexity O(n)
    // Base: There are no more subset to divide
    // Divide: Divide the given set of buildings in two subsets
    // Conquer: Recursively construct skyline for two halves
    // and merge the two skylines


    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        return recursive(B, 0, B.length);
    }

    public static List<Point> recursive(Building[]B, int ptl, int ptr) {
        int sizeb = B.length;

        // Base case: if there's only one building in the set
        // return the height of the building at left x coordinate
        // and 0 for the right x coordinate

        if (ptl-ptr<=1) {
            ArrayList<Point> result = new ArrayList<Point>();
            result.add(new Point(B[ptl].l, B[ptl].h));
            result.add(new Point(B[ptl].r, 0));
            return result;
        }

        // otherwise, divide it into two subsets
        List<Point> resultl = recursive(B, ptl, sizeb/2);
        List<Point> resultr = recursive(B, sizeb/2, ptr);

        // then, return the merged results
        return merge(resultl, resultr);
    }

    public static List<Point> merge(List<Point> resultl, List<Point> resultr) {
        List<Point> heights = new ArrayList<Point>();
        // Initialize pointers
        int pty = 0;
        int ptx = 0;


        // iterate through the arraylist until it doesn't have any element inside
        while(ptx < resultl.size() || pty < resultr.size()) {
            // starting from last element, compare the values of two subsets
            // set the ptx to be at whichever the bigger one is
            if (resultl.get(ptx).x == resultr.get(pty).x) {
                // if both pointers have same x coordinates
                // add whoever has higher heights
                heights.add(new Point(resultl.get(ptx).x, Math.max(resultl.get(ptx).y, resultr.get(pty).y)));
                ptx++; pty++;
            }
            else if (resultl.get(ptx).x < resultr.get(pty).x) {
                // resultl's x coordinate is smaller than resultr's
                // start the pointer from resultl
                heights.add(new Point(resultl.get(ptx).x, Math.max(resultl.get(pty).y,resultr.get(ptx).y)));
                ptx++;
            } else {
                // start the pointer from resultr
                heights.add(new Point(resultl.get(pty).x, Math.max(resultr.get(pty).y, resultl.get(pty).y)));
                pty++;
            }

            if (heights.get(heights.size()-1).y == heights.get(heights.size()-2).y) {
                // if the last two points have same y value, remove the last one
                resultr.remove(resultl.size() - 1);
            }
        }

        return heights;
    }
}
