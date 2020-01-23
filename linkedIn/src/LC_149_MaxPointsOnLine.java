import java.util.HashMap;
import java.util.Map;

public class LC_149_MaxPointsOnLine {

    private int maxPoints(int[][] points) {

        if (points.length == 0) {
            return 0;
        }
        Map<Slop, Integer> slopMap = new HashMap<>();

        //Do we consider overlapping points too.

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int overlappingPoints = 0, verticalPoints = 0, curMax = 0;

            for (int j = i + 1; j < points.length; j++) {

                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                if (x1 == x2 && y1 == y2) {
                    overlappingPoints++;
                } else if (x1 == x2) {
                    verticalPoints++;
                } else {
                    int deltaY = y2 - y1;
                    int deltaX = x2 - x1;

                    int gcd = gcd(deltaX, deltaY);

                    int deY = deltaY / gcd;
                    int delX = deltaX / gcd;
                    Slop key = new Slop(deY, delX);

                    slopMap.merge(key, 1, Integer::sum);
                    curMax = Integer.max(curMax, slopMap.get(key));
                }

                curMax = Integer.max(curMax, verticalPoints);
            }
            max = Integer.max(max, curMax + overlappingPoints + 1);
            slopMap.clear();
        }
        return max;

    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private static class Slop {
        int y, x;

        Slop(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + y + x;
            return result;
        }

        @Override
        public boolean equals(Object slop1) {
            if (this == slop1)
                return true;
            if (slop1 == null)
                return false;
            if (getClass() != slop1.getClass())
                return false;
            Slop slop = (Slop) slop1;
            return this.x == slop.x && this.y == slop.y;
        }
    }


    public static void main(String[] args) {
//        int [][] input = {{1,1},{2,2},{3,3}};//,{4,1},{2,3},{1,4}};
        int[][] input = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(new LC_149_MaxPointsOnLine().maxPoints(input));
    }
}
