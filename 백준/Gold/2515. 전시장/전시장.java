import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int h, price;

        public Node(int h, int price) {
            this.h = h;
            this.price = price;
        }

        @Override
        public int compareTo(Node o) {
            if (h == o.h) {
                return price - o.price;
            }
            return h - o.h;
        }
    }

    public static int upperBinary(int left, int right, int target, int[][] arr1) {

        while (left < right) {

            int mid = (left + right) >> 1;

            if (arr1[mid][0] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[][] arr1 = new int[N + 1][2];
        int[][] dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arr1[i][0] = h;
            arr1[i][1] = price;
        }

        Arrays.sort(arr1, (o1, o2) -> o1[0] - o2[0]);
        // dp[0][0] -> 사진을 골랐을때
        // dp[0][1] -> 사진을 고르지 않았을때
        dp[1][0] = arr1[1][1];

        for (int i = 2; i <= N; i++) {

            int idx = upperBinary(1, i, arr1[i][0] - S, arr1);
            if (idx == i + 1) {
                dp[i][0] = arr1[i][1];
            } else {
                dp[i][0] = Math.max(dp[idx - 1][0], dp[idx - 1][1]) + arr1[i][1];
            }
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            
        }

        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}