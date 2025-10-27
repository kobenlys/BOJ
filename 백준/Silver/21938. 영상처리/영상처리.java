import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[][] vi;
    public static double[][] arr1;

    public static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int x, int y) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        vi[y][x] = true;
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(x, y));
        while (!qu.isEmpty()) {

            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N){
                    continue;
                }

                if(!vi[ny][nx] && arr1[ny][nx] == 255){
                    vi[ny][nx] = true;
                    qu.offer(new Node(nx, ny));
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
        M = Integer.parseInt(st.nextToken());
        arr1 = new double[N][M];
        vi = new boolean[N][M];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr1[i][j] = (double) (r + g + b) / 3;
            }
        }
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr1[i][j] >= T){
                    arr1[i][j] = 255;
                }else{
                    arr1[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!vi[i][j] && arr1[i][j] == 255){
                    bfs(j, i);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}