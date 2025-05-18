import java.io.*;
import java.util.*;

public class Main {

    public static int N, K, answer;
    public static int[][] arr1;
    public static int[][] dist;
    public static boolean[] vi;

    public static void dfs(int start, int value){

        boolean isOk = false;

        for (int i = 0; i < N; i++) {
            if (!vi[i]) {
                vi[i] = true;
                dfs(i, value + dist[start][i]);
                vi[i] = false;
                isOk = true;
            }
        }

        if (!isOk) {
            answer = Math.min(answer, value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr1 = new int[N][N];
        dist = new int[N][N];
        vi = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;
                if (i == j) {
                    dist[i][j] = 0;
                }
            }
        }

        for (int w = 0; w < N; w++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j],  Math.min(arr1[i][j], arr1[i][w] + arr1[w][j]));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        vi[K] = true;
        dfs(K, 0);
        System.out.println(answer);
    }
}