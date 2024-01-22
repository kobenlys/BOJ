import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, W;
    public static int[] dist;
    public static ArrayList<ArrayList<node>> arr1;

    public static class node implements Comparable<node> { // 간선을 담는 객체
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

    public static boolean bellmanFord() { // 벨만-포드 알고리즘
        boolean isNegative = false;

        for (int i = 0; i < N; i++) {
            isNegative = false;
            for (int j = 0; j < N; j++) {
                for (node nd : arr1.get(j)) {
                    // 저장된 dist 값보다 더 작은값, 즉 음의 사이클이 있다면
                    if (dist[nd.goal] > dist[j] + nd.time) {
                        // 초기화
                        dist[nd.goal] = dist[j] + nd.time;
                        isNegative = true;
                        if (i == N - 1) {
                            // 마지막 노드까지 계속 업데이트가 되었다면
                            // 이 그래프는 음의 사이클이 존재한다
                            return true;
                        }
                    }
                }
            }
            // 도중에 업데이트 되지 않는 노드가 있다면
            // 음의 사이클은 존재하지 않는다.
            if (!isNegative) {
                break;
            }
        }
        return isNegative;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // ArrayList를 사용한 인접리스트. 간선을 표현한다.
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
                    // 간선 표시
                    arr1.get(s).add(new node(e, t));
                    arr1.get(e).add(new node(s, t));
                } else {
                    // 웜홀 , 음수 간선 표시
                    arr1.get(s).add(new node(e, -t));
                }
            }
            // 알고리즘 호출 + 출력, 삼항연산자 이용
            sb.append(bellmanFord() ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }
}
