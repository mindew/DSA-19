public class PeakFinding {
    // Base:If middle element is not smaller han any of fits neighbors, return
    // Divide: Divide the array into two, and compare the middle value with its
    // neighbors. If left element is smaller, peak is in its left half
    // If right element is smaller, peak is in its right half
    // Conquer: Recurse until the algorithm finds the peak


    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        return findPeakRecurse(nums, 0, nums.length - 1, nums.length);
    }

    public static int[] findTwoDPeak(int[][] nums) {
        // time complexity: O(rows * log (columns))
        // space complexity: O(columns/2)
        return findTwoDPeakRecurse(nums, 0, nums[0].length - 1);
    }

    static int[] findTwoDPeakRecurse(int[][] nums, int lo, int hi) {
        // calculate the maximum element in the mid column
        int mid = (lo+hi)/2;
        int max = 0;
        int max_index = 0;
        int[] result = new int[2];

        System.out.println("mid: " + mid);
        // find the maximum element in the mid column
        for (int i = 0; i < nums.length; i++) {
            if (nums[i][mid] > max) {
                max = nums[i][mid];
                max_index = i;
            }
        }

        System.out.println(max_index);
        if ((mid >= 0) || (mid <= nums.length-1)) {
            // edgecase: if mid is first or last column, max is a peak
            if (mid == 0 || mid == hi) {
                result[0] = max_index;
                result[1] = mid;
                System.out.println("" + result[0] + "," + result[1]);
                return result;
            }

            // basecase: if mid column max is a peak
            if (max >= nums[max_index][mid - 1] && max >= nums[max_index][mid + 1]) {
                result[0] = max_index;
                result[1] = mid;
                return result;
            }

            // if max is less than its left, recurse on the left half
            if (max < nums[max_index][mid - 1]) {
                return findTwoDPeakRecurse(nums, lo, mid);
            }

            // if max is less than its right, recurse on the right half
            if (max < nums[max_index][mid + 1]) {
                return findTwoDPeakRecurse(nums, mid + 1, hi);
            }
        }
        return result;
    }

    static int findPeakRecurse(int[] nums, int lo, int hi, int length) {
        int mid = (lo + hi) / 2;

        // edgecase: corner elements are peaks
        if ((lo == 0) || (hi == length - 1)) {
            if (nums[lo] > nums[lo + 1]) {
                return lo;
            } else if (nums[hi] > nums[hi - 1]) {
                return hi;
            }
        }

        // edgecase: mid is peak or mid is corner elements
        if ((mid == 0) || (nums[mid] > nums[mid - 1]) && ((mid == length - 1) || nums[mid] > nums[mid + 1]))
            return mid;
        else if (nums[mid] < nums[mid + 1]) {
            // if the peak is in the right half
            return findPeakRecurse(nums, mid + 1, hi, length);
        } else {
            // if the peak is in the left hlaf
            return findPeakRecurse(nums, lo, mid - 1, length);
        }
    }

}
