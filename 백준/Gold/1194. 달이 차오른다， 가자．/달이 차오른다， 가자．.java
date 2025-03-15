import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static boolean[][][] vi;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class Node{
        int x, y, step, pos;
        public Node(int x, int y, int step, int pos) {
            this.x = x;
            this.y = y;
            this.step = step;
            this.pos = pos;
        }
    }

    public static void bfs(int x, int y){
        Queue<Node> qu = new ArrayDeque<>();
        vi = new boolean[N][M][64];
        qu.offer(new Node(x, y,0, 0));

        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if (!vi[ny][nx][nd.pos]) {
                    if(arr1[ny][nx] == '1'){
                        System.out.println(nd.step+1);
                        System.exit(0);
                    }

                    if (arr1[ny][nx] == '.' || arr1[ny][nx] == '0') {
                        vi[ny][nx][nd.pos] = true;
                        qu.offer(new Node(nx, ny, nd.step+1, nd.pos));
                        continue;
                    }

                    if(arr1[ny][nx] >= 'a'  &&  arr1[ny][nx] <= 'f'){
                        int nextPos = 1 << (arr1[ny][nx] - 'a');
                        nextPos = nd.pos | nextPos;
                        vi[ny][nx][nextPos] = true;
                        qu.offer(new Node(nx, ny, nd.step + 1, nextPos));
                        continue;
                    }

                    if(arr1[ny][nx] >= 'A'  &&  arr1[ny][nx] <= 'F'){
                        if ((nd.pos & 1 << (arr1[ny][nx] - 'A')) != 0) {
                            vi[ny][nx][nd.pos] = true;
                            qu.offer(new Node(nx, ny, nd.step + 1, nd.pos));
                        }
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
        arr1 = new char[N][M];
        int sX = 0, sY = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == '0') {
                    sX = j;
                    sY = i;
                }
            }
        }
        bfs(sX, sY);
        System.out.println(-1);
    }
}