import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, res = Integer.MAX_VALUE;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static ArrayList<node> stk;
    public static Queue<node> qu = new LinkedList<>();
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class node {
        int x, y, cnt;

        public node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void moveFire() {
        ArrayList<node> tmp = new ArrayList<>();

        for (int i = 0; i < stk.size(); i++) {
            node fire = stk.get(i);
            for (int j = 0; j < 4; j++) {
                int nx = fire.x + dx[j];
                int ny = fire.y + dy[j];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (arr1[ny][nx] == '.') {
                    arr1[ny][nx] = 'F';
                    tmp.add(new node(nx, ny, 0));
                }
            }
        }

        stk = new ArrayList<>(tmp);
    }

    public static void bfs() {

        int flag = 0;


        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (nd.cnt != flag) {
                moveFire();
                flag = nd.cnt;
            }

            if(arr1[nd.y][nd.x] == 'F') continue;

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N) {
                  
                    System.out.println(nd.cnt + 1);
                    System.exit(0);
                }

                if (!vi[ny][nx] && arr1[ny][nx] == '.') {
                    vi[ny][nx] = true;
                    qu.offer(new node(nx, ny, nd.cnt + 1));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new char[N][M];
        vi = new boolean[N][M];
        stk = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == 'J') {
                    qu.offer(new node(j, i, 0));
                    vi[i][j] = true;
                    arr1[i][j] = '.';
                } else if (arr1[i][j] == 'F') {
                    stk.add(new node(j, i, 0));
                }
            }
        }

        bfs();
        System.out.println("IMPOSSIBLE");
    }
}