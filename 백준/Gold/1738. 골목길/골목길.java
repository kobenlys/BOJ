import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cnt = 0;
    public static ArrayList<ArrayList<node>> arr1;
    public static long[] dist;
    public static ArrayList<Integer> trace = new ArrayList<>();
    public static boolean[] vi;

    public static class node {
        int goal, money;

        public node(int goal, int money) {
            this.goal = goal;
            this.money = money;
        }
    }

    // 양수 사이클 존재 하나 -> N번 목적지에 피해를 안주는 경우
    // 양수 사이클 존재하지 않는 경우
    public static boolean bellmanFord() {
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (node nd : arr1.get(j)) {
                    if (dist[nd.goal] < dist[j] + nd.money) {
                        dist[nd.goal] = dist[j] + nd.money;

                        if (i == N - 1) {
                            vi = new boolean[N];
                            if (isConnect(j)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isConnect(int val) {

        boolean flag = false;

        if (val == N - 1) {
            return true;
        }

        for (node nd : arr1.get(val)) {
            if (!vi[nd.goal]) {
                vi[nd.goal] = true;
                // 하나 이상의 값이 '참'일때 flag = true 이다.
                flag |= isConnect(nd.goal);
            }
        }

        return flag;
    }

    public static void printAnswer(int start) {

        if (start == N - 1) {
            for (int i = 0; i < trace.size(); i++) {
                System.out.print(trace.get(i)+" ");
            }
            System.exit(0);
        }

        for (node nd : arr1.get(start)) {
            if (!vi[nd.goal] && dist[nd.goal] == dist[start] + nd.money) {
                vi[nd.goal] = true;
                trace.add(nd.goal+1);
                printAnswer(nd.goal);
                trace.remove(trace.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N];
        arr1 = new ArrayList<>();
        vi = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            arr1.get(s).add(new node(e, v));
        }

        if (bellmanFord()) {
            System.out.println(-1);
        } else {
            vi = new boolean[1000];
            trace.add(1);
            printAnswer(0);
        }
    }
}
