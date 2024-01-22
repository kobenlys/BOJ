import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, W, INF = Integer.MAX_VALUE;
    public static ArrayList<ArrayList<node>> arr1;
    public static int[] dist;


    public static class node implements Comparable<node> {
        int goal, time;

        public node(int goal, int time) {
            this.goal = goal;
            this.time = time;
        }

        @Override
        public int compareTo(node o) {
            return goal - o.goal;
        }
    }

    public static boolean dijkstra(int start) {
        boolean isNegative = false;

        for (int i = 0; i < N; i++) {
            isNegative = false;

            for (int j = 0; j < N; j++) {
                for (node nd : arr1.get(j)) {

                    if (dist[nd.goal] > dist[j] + nd.time) {
                        dist[nd.goal] = dist[j] + nd.time;
                        isNegative = true;
                        if (i == N - 1) {
                            return true;
                        }
                    }
                }
            }
            if (!isNegative) {
                break;
            }
        }
        return isNegative;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N];
            arr1 = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                arr1.add(new ArrayList<>());
            }

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int t = Integer.parseInt(st.nextToken());

                if (i < M) {
                    arr1.get(s).add(new node(e, t));
                    arr1.get(e).add(new node(s, t));
                } else {
                    arr1.get(s).add(new node(e, -t));
                }
            }
            System.out.println(dijkstra(0) ? "YES" : "NO");
        }
    }
}
