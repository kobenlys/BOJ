import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, size, cnt, tmp;
    public static int[][] arr1;
    public static boolean[][] vi;
    public static Queue<node> qu = new LinkedList<>();
    public static PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {
        @Override
        public int compare(node o1, node o2) {
            if (o1.step != o2.step) {
                return o1.step - o2.step;
            } else if (o1.y != o2.y) {
                return o1.y - o2.y;
            } else {
                return o1.x - o2.x;
            }
        }
    });

    public static class node {
        int x, y, step;

        public node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void bfs(int x, int y) {
        findFish(x, y);

        while (!pq.isEmpty()) {
            node site = pq.poll();
            cnt += site.step;
            arr1[site.y][site.x] = 0;
            tmp++;
            if (size == tmp) {
                size++;
                tmp = 0;
            }
            findFish(site.x, site.y);
        }
    }

    public static void findFish(int x, int y) {
        int[] dx = {0, -1, 1, 0}; // 상 좌 우 하
        int[] dy = {-1, 0, 0, 1};
        pq.clear();
        qu.clear();
        vi = new boolean[N][N];
        qu.offer(new node(x, y, 0));

        while (!qu.isEmpty()) {
            node nd = qu.poll();
            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                int nStep = nd.step + 1;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!vi[ny][nx]) {

                    if (arr1[ny][nx] != 0 && arr1[ny][nx] < size) {
                        vi[ny][nx] = true;
                        pq.offer(new node(nx, ny, nStep));
                    } else if (arr1[ny][nx] == 0 || arr1[ny][nx] == size) {
                        vi[ny][nx] = true;
                        qu.offer(new node(nx, ny, nStep));
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        vi = new boolean[N][N];
        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 9) {
                    x = j;
                    y = i;
                    arr1[i][j] = 0;
                }
            }
        }
        size = 2;
        tmp = 0;
        bfs(x, y);
        System.out.println(cnt);
    }
}