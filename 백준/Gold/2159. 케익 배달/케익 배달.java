import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static ArrayList<ArrayList<node>> arr1;
    public static long[][] dist;

    public static class node {
        int x, y, idx, dir;

        public node(int x, int y, int idx, int dir) {
            this.x = x;
            this.y = y;
            this.idx = idx;
            this.dir = dir;
        }
    }

    public static void dijkstra(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, 0, -1, 1};
        int[] dy = {0, -1, 1, 0, 0};
        // 초깃값 설정
        dist[0][0] = dist[0][1] = dist[0][2]
                = dist[0][3] = dist[0][4] = 0;
        qu.offer(new node(x, y, 0, 0));

        while (!qu.isEmpty()) {

            node now = qu.poll();
            if (now.idx + 1 == N+1) continue;

            for (int i = 0; i < arr1.get(now.idx + 1).size(); i++) {

                node tmp = arr1.get(now.idx + 1).get(i);

                for (int j = 0; j < 5; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];


                    if (nx < 1 || ny < 1 || nx > 100_000 || ny > 100_000) continue;

                    long val = Math.abs(nx - now.x) + Math.abs(ny - now.y);

                    if (dist[tmp.idx][j] > dist[now.idx][now.dir] + val) {

                        dist[tmp.idx][j] = dist[now.idx][now.dir] + val;
                        qu.offer(new node(nx, ny, tmp.idx, j));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long answer = Long.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        arr1 = new ArrayList<>();
        dist = new long[N+1][5];

        for (int i = 0; i < N+1; i++) {
            arr1.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr1.get(i).add(new node(x, y, i, 0));
        }

        dijkstra(X, Y);

        for (int j = 0; j < 5; j++) {
            answer = Math.min(answer, dist[N][j]);
        }
        System.out.println(answer);
    }
}