import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, res;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] provider;
    public static boolean[] cutGraph;
    public static boolean[] vi;

    public static void dfs(int node) {
        for (int e : arr1.get(node)) {
            if (!cutGraph[e] && !vi[e]) {
                res++;
                vi[e] = true;
                dfs(e);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();
        provider = new boolean[26];


        for (int i = 0; i < 26; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            char s = input.charAt(0);
            char e = input.charAt(2);
            arr1.get(s - 'A').add(e - 'A');
            provider[e - 'A'] = true;
        }

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        cutGraph = new boolean[N];

        for (int i = 0; i < T; i++) {
            char c = st.nextToken().charAt(0);
            cutGraph[c - 'A'] = true;
        }

        res = 0;
        for (int i = 0; i < N; i++) {
            if (!provider[i] && !cutGraph[i]) {
                vi = new boolean[26];
                dfs(i);
            }
        }
        System.out.println(res);
    }
}