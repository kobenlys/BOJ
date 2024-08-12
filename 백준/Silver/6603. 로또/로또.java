import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static List<Integer> list = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int start, int idx) {

        if (start == 6) {
            for (int e : list) {
                sb.append(e).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = idx; i < arr1.length; i++) {
            list.add(arr1[i]);
            dfs(start + 1, i + 1);
            list.remove(list.size() - 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            arr1 = new int[N];

            for (int i = 0; i < N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            sb.append("\n");

        }

        System.out.println(sb);
    }
}