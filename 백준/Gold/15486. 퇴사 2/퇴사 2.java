import java.io.*;
import java.util.*;

public class Main {

    public static class Node {
        int day, cost;

        public Node(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node[] arr1 = new Node[N + 1];
        int[] dp = new int[N + 1001];
        int tmpMax = 0;
        int max = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr1[i] = new Node(d, c);
        }

        for (int i = N; i >= 1; i--) {
            dp[i] = dp[i + arr1[i].day];
            if(i + arr1[i].day - 1 <= N){
                dp[i] += arr1[i].cost;
            }

            tmpMax = Math.max(tmpMax, dp[i]);
            dp[i] = tmpMax;
        }

        System.out.println(tmpMax);
    }
}