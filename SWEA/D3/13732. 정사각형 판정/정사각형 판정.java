import java.io.*;
import java.util.*;

public class Solution {
    public static int N, minX, minY, maxX, maxY, cnt;
    public static char[][] arr1;
    public static boolean[][] vi;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(x, y));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        cnt++;
        vi[y][x] = true;


        while (!qu.isEmpty()) {

            node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if (!vi[ny][nx] && arr1[ny][nx] == '#') {
                    vi[ny][nx] = true;
                    cnt++;
                    minX = Math.min(minX, nx);
                    minY = Math.min(minY, ny);
                    maxX = Math.max(maxX, nx);
                    maxY = Math.max(maxY, ny);
                    qu.offer(new node(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            minX = 100; minY=100; maxX=0; maxY=0; cnt=0;
            arr1 = new char[N][N];
            vi = new boolean[N][N];

            boolean isPossible = false;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = str.charAt(j);
                }
            }

            st:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr1[i][j] == '#' && !vi[i][j]) {
                        bfs(j, i);
                    }
                }
            }

            if (maxX - minX + 1 == maxY - minY + 1) {
                int n = maxX - minX + 1;
                if (n * n == cnt) {
                    isPossible = true;
                }
            }


            sb.append("#").append(t).append(" ");
            sb.append(isPossible ? "yes" : "no");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}