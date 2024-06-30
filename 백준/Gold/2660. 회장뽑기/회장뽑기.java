import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;

    public static void floydWarshall() {

        for (int fw = 1; fw <= N; fw++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    if (arr1[i][j] > arr1[i][fw] + arr1[fw][j] + 1) {
                        arr1[i][j] = arr1[i][fw] + arr1[fw][j] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> ans = new ArrayList<>();
        arr1 = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                arr1[i][j] = 10000000;
            }
            arr1[i][i] = 0;
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s == -1) break;
            arr1[s][e] = arr1[e][s] = 0;
        }

        floydWarshall();

        int depth = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int tmp = 0;
            boolean isPossible = true;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (arr1[i][j] == Integer.MAX_VALUE) {
                    isPossible = false;
                    break;
                }
                tmp = Math.max(tmp, arr1[i][j]);
            }
            tmp++;
            
            if (isPossible && depth >= tmp) {
                if (depth > tmp) ans.clear();
                depth = tmp;
                ans.add(i);
            }
        }

        Collections.sort(ans);
        sb.append(depth).append(" ").append(ans.size()).append("\n");
        for (int e : ans) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }
}