import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static char[][] arr1;
    public static int[][] garbage;
    public static Score[][] vi;
    public static Queue<Node> qu = new ArrayDeque<>();

    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static class Node {
        int x, y, cnt1, cnt2;

        public Node(int x, int y, int cnt1, int cnt2) {
            this.x = x;
            this.y = y;
            this.cnt1 = cnt1;
            this.cnt2 = cnt2;
        }
    }

    public static class Score {
        int cnt1, cnt2;

        public Score(int cnt1, int cnt2) {
            this.cnt1 = cnt1;
            this.cnt2 = cnt2;
        }
    }

    public static void bfs() {
        while (!qu.isEmpty()) {
            Node nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                int beforeCost1 = vi[nd.y][nd.x].cnt1;
                int beforeCost2 = vi[nd.y][nd.x].cnt2 + garbage[ny][nx];
                if (arr1[ny][nx] == 'g') {
                    beforeCost1 += 1;
                }


                if (vi[ny][nx].cnt1 == -1) {
                    vi[ny][nx].cnt1 = beforeCost1;
                    vi[ny][nx].cnt2 = beforeCost2;
                    qu.offer(new Node(nx, ny, 0, 0));
                    continue;
                }

                if (vi[ny][nx].cnt1 > beforeCost1) {
                    vi[ny][nx].cnt1 = beforeCost1;
                    vi[ny][nx].cnt2 = beforeCost2;
                    qu.offer(new Node(nx, ny, 0, 0));
                    continue;
                }

                if (vi[ny][nx].cnt1 == beforeCost1 && vi[ny][nx].cnt2 > beforeCost2) {
                    vi[ny][nx].cnt2 = beforeCost2;
                    qu.offer(new Node(nx, ny, 0, 0));
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
        arr1 = new char[N][M];
        garbage = new int[N][M];
        vi = new Score[N][M];

        int fX = 0, fY = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                vi[i][j] = new Score(-1, 0);
                if (arr1[i][j] == 'S') {
                    vi[i][j].cnt1 = 0;
                    vi[i][j].cnt2 = 0;
                    qu.offer(new Node(j, i, 0, 0));
                }

                if (arr1[i][j] == 'F') {
                    fX = j;
                    fY = i;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 'g') {
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                        if(arr1[ny][nx] != '.') continue;
                        garbage[ny][nx] = 1;
                    }
                }
            }
        }

        bfs();
        System.out.println(vi[fY][fX].cnt1 +" "+ vi[fY][fX].cnt2);

    }
}