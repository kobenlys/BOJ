import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, sum;
    public static boolean[] vi;
    public static ArrayList<ArrayList<Integer>> arr1 = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> arr1Rvrs = new ArrayList<>();


    public static void dfs(int start) {

        if (arr1.get(start).isEmpty()) {
            return;
        }

        for (int i = 0; i < arr1.get(start).size(); i++) {
            int node = arr1.get(start).get(i);
            if (!vi[node]) {
                vi[node] = true;
                sum++;
                dfs(node);
            }
        }
    }

    public static void dfsRvrs(int start) {

        if (arr1Rvrs.get(start).isEmpty()) {
            return;
        }

        for (int i = 0; i < arr1Rvrs.get(start).size(); i++) {
            int node = arr1Rvrs.get(start).get(i);
            if (!vi[node]) {
                vi[node] = true;
                sum++;
                dfsRvrs(node);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
            arr1Rvrs.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr1.get(s).add(e);
            arr1Rvrs.get(e).add(s);
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            sum = 0;
            vi = new boolean[N+1];
            vi[0] = true;
            dfs(i);
            dfsRvrs(i);
            if (N - 1 == sum) {
                ans++;
            }
        }
        System.out.print(ans);
    }
}