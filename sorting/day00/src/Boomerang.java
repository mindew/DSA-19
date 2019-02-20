import java.util.*;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {

        int globalcounter = 0;;

        for (int x = 0; x < points.length; x++) {
            int localcounter = 0;
            Map<Double, Integer> hm = new HashMap<Double, Integer>();
            for (int y = 0; y < points.length; y++) {
                double dist = distancecal((points[x][0]), points[x][1], points[y][0], points[y][1]);
                    hm.put(dist, hm.getOrDefault(dist, 0)+1);
            }

                for (Map.Entry<Double, Integer> entry: hm.entrySet()) {
                    Integer n = entry.getValue();
                    localcounter = localcounter + n*(n-1);
                }
            globalcounter = globalcounter + localcounter;
        }

        return globalcounter;
    }

    public static double distancecal(int x1, int y1, int x2, int y2) {
        double dist = Math.hypot(x1-x2, y1-y2);

        return dist;
    }
}

