import java.io.*;
import java.util.*;

public class Main {

    public static char[][] map;
    public static boolean[][] vi;
    public static List<Node> cache;

    public static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 6 && y < 12;
    }

    public static void bfs(int x, int y) {

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int targetColor = map[y][x];

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        List<Node> tmp = new ArrayList<>();

        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (isRange(nx, ny) && !vi[ny][nx] && targetColor == map[ny][nx]) {
                    vi[ny][nx] = true;
                    tmp.add(new Node(nx, ny));
                    qu.offer(new Node(nx, ny));
                }
            }
        }

        if (tmp.size() >= 4) {
            cache.addAll(tmp);
        }
    }

    public static void onRemove() {
        for (Node rm : cache) {
            map[rm.y][rm.x] = '.';
        }
    }

    public static void onGravity() {

        for (int i = 10; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                char tmpColor = map[i][j];
                map[i][j] = '.';
                for (int k = 1; k < 12; k++) {

                    if (i + k == 12) {
                        map[i + k - 1][j] = tmpColor;
                        break;
                    }
                    if (map[i + k][j] != '.') {
                        map[i + k - 1][j] = tmpColor;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int answer = 0;
        map = new char[12][6];
        cache = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        while (true) {

            vi = new boolean[12][6];
            cache.clear();
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!vi[i][j] && map[i][j] != '.') {
                        bfs(j, i);
                    }
                }
            }

            if (cache.isEmpty()) {
                break;
            }
            onRemove();
            onGravity();
            answer++;
        }
        System.out.println(answer);
    }
}