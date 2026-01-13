class Solution {
    public boolean check(int[][] squares, double y) {
        double area = 0;
        for(int[] square: squares) {
            int bl_x = square[0];
            int bl_y = square[1];

            int tr_x = square[0] + square[2];
            int tr_y = square[1] + square[2];

            int width = square[2];
            
            double bottomHeight = bl_y >= y ? 0 : tr_y <= y ? square[2] : y - bl_y;
            double topHeight = square[2] - bottomHeight;

            area = area + width * bottomHeight;
            area = area - width * topHeight;
        }

        return area < 0;
    }
    
    public double separateSquares(int[][] squares) {
        double i = 0, j = Integer.MAX_VALUE;
        // double ans = -1;
        while(i + 0.00001 <= j) {
            double mid = i + ( j - i ) / 2;
            if(check(squares, mid)) { // checks if bottom area is lesser
                // ans = mid;
                i = mid;
            } else {
                j = mid;
            }
        }
        return i + (j - i) / 2;
    }
}