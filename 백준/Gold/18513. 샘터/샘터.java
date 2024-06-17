import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static Queue<node> qu = new LinkedList<>();
    public static HashSet<Integer> set = new HashSet<>();

    public static class node {
        int num, cnt;

        public node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static long bfs() {
        int[] dx = {1, -1}; // 좌, 우
        long answer = 0;

        st:
        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 2; i++) {
                int nextNum = nd.num + dx[i];
                // 방문 처리
                if (set.contains(nextNum)) continue;
                set.add(nextNum);
                K--; // set에 추가하고, K -1 하기
                answer += nd.cnt + 1;
                if (K == 0) break st; // K 번째 바로 탈출.
                qu.offer(new node(nextNum, nd.cnt + 1));
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
            qu.offer(new node(n, 0));
        }
        System.out.println(bfs());
    }
}