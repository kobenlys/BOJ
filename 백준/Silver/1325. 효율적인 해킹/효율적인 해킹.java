import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, max;
    public static boolean[] vi;
    public static int[] cnt;
    public static ArrayList<ArrayList<Integer>> arr1;

    public static void dfs(int num) {
        for (int e : arr1.get(num)) {
            if (!vi[e]) {
                vi[e] = true;
                cnt[e]++;
                max = Math.max(max, cnt[e]);
                dfs(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> res = new ArrayList<>();
        arr1 = new ArrayList<>();
        cnt = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr1.get(A).add(B);
        }

        for (int i = 1; i <= N; i++) {
            vi = new boolean[N + 1];
            vi[i] = true;
            dfs(i);
        }


        for (int i = 1; i <= N; i++) {
            if (max == cnt[i]) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}