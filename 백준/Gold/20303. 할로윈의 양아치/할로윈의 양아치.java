import java.io.*;
import java.util.*;

public class Main {
    public static int[] parent;
    
    // 부모 찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 집합 합치기
    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        if (from != to) {
            parent[to] = from;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        int[][] arr1 = new int[N + 1][2];
        int[] candyValue = new int[N + 1];
        int[][] dp = new int[N + 1][K];
        
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candyValue[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            union(s, e); // 집합 합치기.
        }

        for (int i = 1; i <= N; i++) {
            int root = find(i);
            arr1[root][0]++; // 인원수
            arr1[root][1] += candyValue[i]; // 해당 집합 사탕 수
        }
        // 각 그룹 인원순 정렬.
        Arrays.sort(arr1, Comparator.comparingInt(o -> o[0]));
        
        // 냅색 알고리즘
        for (int i = 1; i <= N; i++) {
            if(arr1[i][0] == 0) continue;
            for (int j = 1; j < K; j++) {
                if (arr1[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr1[i][0]] + arr1[i][1]);
                }
            }
        }

        System.out.print(dp[N][K - 1]);
    }
}