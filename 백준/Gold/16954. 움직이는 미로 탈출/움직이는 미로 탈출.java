import java.io.*;
import java.util.*;

public class Main {
    public static char[][] arr1;
    public static Queue<Node> qu = new ArrayDeque<>();

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs() {
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};

        int size = qu.size();

        for (int t = 0; t < size; t++) {
            Node nd = qu.poll();

            if (arr1[nd.y][nd.x] == '#') continue;
            qu.offer(new Node(nd.x, nd.y));
            
            for (int i = 0; i < 8; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;

                if (arr1[ny][nx] == '.') {

                    if (nx == 7 && ny == 0) {
                        System.out.println(1);
                        System.exit(0);
                    }

                    arr1[ny][nx] = 'X';
                    qu.offer(new Node(nx, ny));
                }
            }
        }
    }

    public static void moveBlocks() {

        for (int i = 0; i < 8; i++) {
            arr1[7][i] = '.';
        }

        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {

                if (arr1[i][j] == '#') {
                    arr1[i][j] = '.';
                    arr1[i + 1][j] = '#';
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        arr1 = new char[8][8];

        for (int i = 0; i < 8; i++) {
            arr1[i] = br.readLine().toCharArray();
        }
        qu.offer(new Node(0, 7));

        while (true) {

            bfs();
            if (qu.isEmpty()) {
                System.out.println(0);
                System.exit(0);
            }
            moveBlocks();
        }
    }
}