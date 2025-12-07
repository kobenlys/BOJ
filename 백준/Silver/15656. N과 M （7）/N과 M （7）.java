import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static StringBuilder sb = new StringBuilder();
    public static int[] arr1;

    public static void dfs(int depth, int[] cache) {

        if (depth == M) {
            Arrays.stream(cache).forEach(e -> sb.append(e).append(" "));
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            cache[depth] = arr1[i];
            dfs(depth + 1, cache);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        arr1 = Arrays.stream(arr1).distinct().sorted().toArray();

        dfs(0, new int[M]);
        System.out.println(sb);
    }
}