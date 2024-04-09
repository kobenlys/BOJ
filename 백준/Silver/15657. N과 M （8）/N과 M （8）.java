import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[] arr1;
    public static ArrayList<Integer> ans = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int start, int idx) {

        if (M == start) {
            for (int i = 0; i < M; i++) {
                sb.append(ans.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < N; i++) {
            ans.add(arr1[i]);
            dfs(start + 1, i);
            ans.remove(ans.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        dfs(0, 0);
        System.out.print(sb);
    }
}