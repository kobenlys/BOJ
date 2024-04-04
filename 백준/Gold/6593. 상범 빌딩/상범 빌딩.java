import java.io.*;
import java.util.*;

public class Main {
    public static int N, X, Y;
    public static char[][][] arr1;
    public static int[][][] vi;

    public static class node {
        int z, x, y;

        public node(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static String bfs(int sZ, int sX, int sY) {
        Queue<node> qu = new LinkedList<>();
        // 상하좌우위아래
        int[] dz = {0, 0, 0, 0, -1, 1};
        int[] dx = {0, 0, -1, 1, 0, 0};
        int[] dy = {-1, 1, 0, 0, 0, 0};

        qu.offer(new node(sZ, sX, sY));
        int time = -1;
        
        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (arr1[nd.z][nd.y][nd.x] == 'E') {
                time = vi[nd.z][nd.y][nd.x];
            }

            for (int i = 0; i < 6; i++) {
                int nz = nd.z + dz[i];
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nz < 0 || nx < 0 || ny < 0 ||
                        nz >= N || nx >= X || ny >= Y) continue;

                if (vi[nz][ny][nx] == 0) {

                    if (arr1[nz][ny][nx] != '#') {
                        vi[nz][ny][nx] = vi[nd.z][nd.y][nd.x] + 1;
                        qu.offer(new node(nz, nx, ny));
                    }

                } else if (vi[nd.z][nd.y][nd.x] + 1 < vi[nz][ny][nx]) {
                    if (arr1[nz][ny][nx] != '#') {
                        vi[nz][ny][nx] = vi[nd.z][nd.y][nd.z] + 1;
                        qu.offer(new node(nz, nx, ny));
                    }
                }
            }
        }

        if (time == -1) return "Trapped!";
        return "Escaped in " + time + " minute(s).";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            if (N == 0 && Y == 0 && X == 0) break;

            arr1 = new char[N][Y][X];
            vi = new int[N][Y][X];
            int z = 0, y = 0, x = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < Y; j++) {
                    String input = br.readLine();
                    if (input.isEmpty()) {
                        j--;
                        continue;
                    }
                    for (int k = 0; k < X; k++) {
                        arr1[i][j][k] = input.charAt(k);
                        if (arr1[i][j][k] == 'S') {
                            z = i;
                            y = j;
                            x = k;
                        }
                    }
                }
            }
            br.readLine();
            sb.append(bfs(z, x, y)).append("\n");
        }
        System.out.print(sb);
    }
}