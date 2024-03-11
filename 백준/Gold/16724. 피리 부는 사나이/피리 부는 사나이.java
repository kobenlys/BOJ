import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, cntSafeZone;
    public static char[][] arr1;
    public static int[][] vi;
    public static Queue<node> trace = new LinkedList<>();


    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void checkTrace(int flag) {

        while (!trace.isEmpty()) {
            node nd = trace.poll();
            vi[nd.y][nd.x] = flag;
        }
    }

    public static void findDeadLock() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (arr1[i][j] == 'R') {
                    if (arr1[i][j + 1] == 'L') {
                        vi[i][j] = vi[i][j + 1] = ++cntSafeZone;
                    }
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i][j] == 'U') {
                    if (arr1[i - 1][j] == 'D') {
                        vi[i][j] = vi[i - 1][j] = ++cntSafeZone;
                    }
                }
            }
        }
    }

    public static void findCircle() {
        Queue<node> qu = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (vi[i][j] == 0) {
                    trace.clear();
                    trace.offer(new node(j, i));
                    qu.offer(new node(j, i));
                    vi[i][j] = ++cntSafeZone;

                    while (!qu.isEmpty()) {

                        node nd = qu.poll();
                        char nowDir = arr1[nd.y][nd.x];
                        int dx = 0, dy = 0;
                        switch (nowDir) {
                            case 'U':
                                dx = nd.x;
                                dy = nd.y - 1;
                                break;
                            case 'D':
                                dx = nd.x;
                                dy = nd.y + 1;
                                break;
                            case 'R':
                                dx = nd.x + 1;
                                dy = nd.y;
                                break;
                            case 'L':
                                dx = nd.x - 1;
                                dy = nd.y;
                                break;
                        }

                        if (vi[dy][dx] == 0) {
                            vi[dy][dx] = cntSafeZone;
                            qu.offer(new node(dx, dy));
                            trace.offer(new node(dx, dy));
                        } else {
                            if (vi[dy][dx] != cntSafeZone) {
                                checkTrace(vi[dy][dx]);
                                cntSafeZone--;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new char[N][M];
        vi = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
            }
        }

        findDeadLock();
        findCircle();
        System.out.println(cntSafeZone);
    }
}