import java.io.*;
import java.util.*;

public class Main {
    public static int N, answer = -1;
    public static boolean[] vi;
    public static ArrayList<ArrayList<node>> arr1;

    public static class node {
        int e, v;

        public node(int e, int v) {
            this.e = e;
            this.v = v;
        }
    }

    public static void dfs(int start, int cnt, int sum) {

        if (vi[start]) {

            if (start == 0 && cnt == N) {
                answer = Math.max(answer, sum);
            }
            return;
        }

        for (int i = 0; i < arr1.get(start).size(); i++) {
            node nd = arr1.get(start).get(i);
            vi[start] = true;
            dfs(nd.e, cnt + 1, sum + nd.v);
            vi[start] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine())+1;
        int M = Integer.parseInt(br.readLine());
        arr1 = new ArrayList<>();
        vi = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(s == e) continue;

            if (arr1.get(s).isEmpty()) {
                arr1.get(s).add(new node(e, v));
            } else {

                for (int j = 0; j < arr1.get(s).size(); j++) {
                    if (arr1.get(s).get(j).e == e) {
                        if (arr1.get(s).get(j).v < v) {
                            arr1.get(s).remove(j);
                            arr1.get(s).add(new node(e, v));
                        }
                        break;
                    }
                    if (j == arr1.get(s).size() - 1) {
                        arr1.get(s).add(new node(e, v));
                    }
                }
            }
        }

        

        dfs(0, 0, 0);
        System.out.println(answer);
    }
}