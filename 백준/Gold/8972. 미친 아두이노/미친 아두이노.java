import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static List<Node>[][] arr1;
    public static List<Node> robots = new ArrayList<>();
    public static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static int[] dy = {1, 1, 1, 0, 0, 0, -1, -1, -1};

    public static class Node {
        int x, y;
        char player;
        boolean isDead;

        public Node(int x, int y, char player, boolean isDead) {
            this.x = x;
            this.y = y;
            this.player = player;
            this.isDead = isDead;
        }
    }

    public static void moveRobot(int x, int y, int idx, int px, int py) {
        int max = Integer.MAX_VALUE;
        int tmpX = 0;
        int tmpY = 0;

        for (int i = 0; i < 9; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            int res = Math.abs(nx - px) + Math.abs(ny - py);
            if (max > res) {
                max = res;
                tmpY = ny;
                tmpX = nx;
            }
        }
        arr1[y][x].remove(0);
        robots.get(idx).isDead = true;
        Node next = new Node(tmpX, tmpY, 'R', false);

        arr1[tmpY][tmpX].add(next);
        robots.add(next);
    }

    public static void deleteRobot() {
        for (int i = 0; i < robots.size(); i++) {
            Node tmp = robots.get(i);
            if (arr1[tmp.y][tmp.x].size() > 1) {
                for (Node nd : arr1[tmp.y][tmp.x]) {
                    nd.isDead = true;
                }
                arr1[tmp.y][tmp.x].clear();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int px = 0, py = 0;
        arr1 = new ArrayList[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char tmp = str.charAt(j);
                arr1[i][j] = new ArrayList<>();

                if (tmp == 'I') {
                    arr1[i][j].add(new Node(j, i, 'I', false));
                    px = j;
                    py = i;
                }
                if (tmp == 'R') {
                    arr1[i][j].add(new Node(j, i, 'R', false));
                    robots.add(arr1[i][j].get(0));
                }
            }
        }

        String str = br.readLine();
        int answer = 0;

        for (int i = 0; i < str.length(); i++) {
            char ctrl = str.charAt(i);

            int dir = ctrl - '0';
            dir--;

            arr1[py][px].remove(0);
            px += dx[dir];
            py += dy[dir];
            arr1[py][px].add(new Node(px, py, 'I', false));

            if (arr1[py][px].size() > 1) {
                System.out.println("kraj " + (i + 1));
                System.exit(0);
            }

            int size = robots.size();

            for (int j = 0; j < size; j++) {
                Node tmp = robots.get(j);
                if (tmp.isDead) {
                    continue;
                }
                moveRobot(tmp.x, tmp.y, j, px, py);
            }

            deleteRobot();

            if (arr1[py][px].isEmpty()) {
                System.out.println("kraj " + (i + 1));
                System.exit(0);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j].isEmpty()) {
                    sb.append('.');
                } else {
                    sb.append(arr1[i][j].get(0).player);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}