import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] vi;

    public static void dfs(int start, int node) {

        if (start == 4) {
            System.out.println(1);
            System.exit(0);
        }

        for (int e : arr1.get(node)) {
            if (!vi[e]) {
                vi[e] = true;
                dfs(start + 1, e);
                vi[e] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr1.get(s).add(e);
            arr1.get(e).add(s);
        }
        for (int i = 0; i < N; i++) {
            vi = new boolean[N];
            vi[i] = true;
            dfs(0,i);
        }
        System.out.println(0);
    }
}
