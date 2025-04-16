import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static int[][] arr1;
    public static int[][][] vi;

    public static class Node {
        int x, y, chance;

        public Node(int x, int y, int chance) {
            this.x = x;
            this.y = y;
            this.chance = chance;
        }
    }

    public static void bfs() {
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(0, 0, 0));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int[] horseX = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] horseY = {-1, -2, -2, -1, 1, 2, 2, 1};


        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(arr1[ny][nx] == 1) continue;

                if (vi[nd.chance][ny][nx] == 0 || vi[nd.chance][ny][nx] > vi[nd.chance][nd.y][nd.x] + 1) {
                    vi[nd.chance][ny][nx] = vi[nd.chance][nd.y][nd.x] + 1;
                    qu.offer(new Node(nx, ny, nd.chance));
                }
            }

            if (nd.chance + 1 > K) continue;

            for (int i = 0; i < 8; i++) {
                int nx = nd.x + horseX[i];
                int ny = nd.y + horseY[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(arr1[ny][nx] == 1) continue;
                if (vi[nd.chance + 1][ny][nx] == 0 || vi[nd.chance + 1][ny][nx] > vi[nd.chance][nd.y][nd.x] + 1) {
                    vi[nd.chance + 1][ny][nx] = vi[nd.chance][nd.y][nd.x] + 1;
                    //System.out.println(vi[nd.chance + 1][ny][nx] + " " + ny + " "+ nx);
                    qu.offer(new Node(nx, ny, nd.chance + 1));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];
        vi = new int[K + 1][N][M];
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        for (int i = 0; i <= K; i++) {
            if(vi[i][N-1][M-1] == 0) continue;
            answer = Math.min(answer, vi[i][N - 1][M - 1]);
        }
        if(N == 1 && M == 1){
            System.out.println(0);
        }else{

            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }
}