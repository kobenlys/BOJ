import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, F, result, cnt;
    public static int[][] arr1;
    public static boolean[][] vi;

    public static Queue<node> qu = new LinkedList<>();
    public static PriorityQueue<node> pq = new PriorityQueue<>();
    public static HashMap<Integer, int[]> map = new HashMap<>();

    public static class node implements Comparable<node> {
        int x, y, fuel, step, flagIdx;

        public node(int x, int y, int fuel, int step, int flagIdx) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
            this.step = step;
            this.flagIdx = flagIdx;
        }

        @Override
        public int compareTo(node o) {
            if (y == o.y) {
                return x - o.x;
            }
            return y - o.y;
        }
    }

    public static void bfs() {
        // 상 좌 우 하
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        while (!qu.isEmpty()) {
            node nd = qu.poll();

            if (nd.fuel < 0) continue;
            if (!pq.isEmpty() && pq.peek().fuel > nd.fuel) continue;

            if (nd.flagIdx == 0 && arr1[nd.y][nd.x] > 1) {
                pq.offer(new node(nd.x, nd.y, nd.fuel, nd.step, arr1[nd.y][nd.x]));
                continue;
            }

            if (nd.flagIdx != 0) {
                int[] coordi = map.get(nd.flagIdx);
                if (coordi[0] == nd.y && coordi[1] == nd.x) {
                    int newFuel = nd.fuel + nd.step * 2;
                    vi = new boolean[N][N];
                    result = newFuel;
                    cnt++;
                    qu.clear();
                    qu.offer(new node(nd.x, nd.y, newFuel, 0, 0));
                    continue;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!vi[ny][nx] && arr1[ny][nx] != 1) {
                    vi[ny][nx] = true;
                    if (nd.flagIdx == 0) {
                        qu.offer(new node(nx, ny, nd.fuel - 1, nd.step, nd.flagIdx));
                    } else {
                        qu.offer(new node(nx, ny, nd.fuel - 1, nd.step + 1, nd.flagIdx));
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        arr1 = new int[N][N];
        vi = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int texiY = Integer.parseInt(st.nextToken()) - 1;
        int texiX = Integer.parseInt(st.nextToken()) - 1;

        qu.offer(new node(texiX, texiY, F, 0, 0));

        for (int i = 2; i <= M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int sY = Integer.parseInt(st.nextToken()) - 1;
            int sX = Integer.parseInt(st.nextToken()) - 1;
            int eY = Integer.parseInt(st.nextToken()) - 1;
            int eX = Integer.parseInt(st.nextToken()) - 1;

            arr1[sY][sX] = i;
            int[] tmp = {eY, eX};
            map.put(i, tmp);
        }


        for (int i = 0; i <= M; i++) {
            bfs();
            if (!pq.isEmpty()) {
                node tmp = pq.poll();
                arr1[tmp.y][tmp.x] = 0;
                vi = new boolean[N][N];
                qu.offer(new node(tmp.x, tmp.y, tmp.fuel, tmp.step, tmp.flagIdx));
                pq.clear();
            }
        }

        System.out.println(cnt == M ? result : -1);
    }
}