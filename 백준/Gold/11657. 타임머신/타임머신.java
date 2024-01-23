import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static long INF = Long.MAX_VALUE;
    public static ArrayList<ArrayList<node>> arr1;
    public static long[] dist;

    public static class node { // 간선 정보 담는 객체
        int goal, time;

        public node(int goal, int time) {
            this.goal = goal;
            this.time = time;
        }
    }

    public static boolean bellmanford(int start) { // 밸만포드알고리즘
        Arrays.fill(dist, INF);
        dist[start] = 0;

        boolean isNegative = false;
        for (int i = 0; i < N; i++) {
            isNegative = false;

            for (int j = 0; j < N; j++) {
                for (node nd : arr1.get(j)) {
                    if (dist[j] != INF && dist[nd.goal] > dist[j] + nd.time) {
                        dist[nd.goal] = dist[j] + nd.time;
                        isNegative = true;
                        if (i == N - 1) {
                            // 마지막까지 초기화 된다면 음의 사이클이 존재한다.
                            return true;
                        }
                    }
                }
            }
            if (!isNegative) {
                // 초기화가 되지 않았다면 음의 사이클 존재 x
                break;
            }
        }
        return isNegative;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();
        dist = new long[N]; // 6000*10000*N = int형 범위 벗어남

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            arr1.get(s).add(new node(e, t));
        }


        // 출력
        if (bellmanford(0)) {
            sb.append(-1);
        } else {
            for (int i = 1; i < N; i++) {
                sb.append(dist[i] == INF ? -1 : dist[i]).append("\n");
            }
        }
        System.out.print(sb);
    }
}
