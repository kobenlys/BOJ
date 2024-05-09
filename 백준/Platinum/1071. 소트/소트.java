import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1 = new int[1001];
    public static ArrayList<Integer> res = new ArrayList<>();

    public static void dfs() {

        if (N == res.size()) {
            StringBuilder sb = new StringBuilder();
            for (int e : res) {
                sb.append(e).append(" ");
            }
            System.out.print(sb);
            System.exit(0);
        }


        for (int i = 0; i <= 1000; i++) {
            if (arr1[i] > 0) {
                arr1[i]--;
                if (res.isEmpty()) {
                    res.add(i);
                    dfs();
                    res.remove(res.size() - 1);
                } else {

                    if (res.get(res.size() - 1) + 1 != i) {
                        res.add(i);
                        dfs();
                        res.remove(res.size() - 1);
                    }
                }
                arr1[i]++;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr1[n]++;
        }
        dfs();
    }
}