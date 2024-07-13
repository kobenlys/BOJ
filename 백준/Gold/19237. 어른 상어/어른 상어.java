import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static int[][] sharkNow;
    public static int[][][] sharkDir;
    public static int[] nowSharkWatch; // 상어 가 바라보는 방향 기록
    public static node[][] sharkSmell;

    public static class node {
        int smell, sharkNum;

        public node(int smell, int sharkNum) {
            this.smell = smell;
            this.sharkNum = sharkNum;
        }
    }

    public static class after {
        int x, y, sharkN;

        public after(int x, int y, int sharkN) {
            this.x = x;
            this.y = y;
            this.sharkN = sharkN;
        }
    }

    public static void smellRemover() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                node now = sharkSmell[i][j];
                if (now.smell == 0) continue;
                if (now.smell == K && sharkNow[i][j] > 0) continue;
                int t = now.smell - 1;
                if (t == 0) {
                    sharkSmell[i][j] = new node(0, 0);
                } else {
                    sharkSmell[i][j] = new node(t, now.sharkNum);
                }
            }
        }
    }

    public static void sharkMover() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        // 1. 아무 냄새가 없는 칸 = sharkSmell -> 0인칸 ㅇㅇ
        // 2. 내 냄새가 나는 칸 = sharkSmell.sharkNum == 현재 상어 냄새
        // 공통 사항 -> 상어가 바라보는 위치 별 우선순위는 공통으로 적용한다

        List<after> sharkAfter = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int sN = sharkNow[i][j];
                if (sN > 0) {
                    // 1번 우선순위 적용
                    boolean isActive = false;

                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[sharkDir[sN][nowSharkWatch[sN]][k]];
                        int ny = i + dy[sharkDir[sN][nowSharkWatch[sN]][k]];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        if (sharkSmell[ny][nx].smell == 0) {
                            sharkAfter.add(new after(nx, ny, sN));
                            sharkNow[i][j] = 0;
                            nowSharkWatch[sN] = sharkDir[sN][nowSharkWatch[sN]][k];
                            isActive = true;
                            break;
                        }
                    }


                    if (!isActive) {
                        // 2번 우선순위.
                        for (int k = 0; k < 4; k++) {
                            int nx = j + dx[sharkDir[sN][nowSharkWatch[sN]][k]];
                            int ny = i + dy[sharkDir[sN][nowSharkWatch[sN]][k]];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                            if (sharkSmell[ny][nx].sharkNum == sN) {
                                sharkAfter.add(new after(nx, ny, sN));
                                sharkNow[i][j] = 0;
                                nowSharkWatch[sN] = sharkDir[sN][nowSharkWatch[sN]][k];
                                break;
                            }
                        }
                    }
                }
            }
        }

        sharkAfter.sort(((o1, o2) -> o1.sharkN - o2.sharkN));

        for (after nd : sharkAfter) {
            if (sharkNow[nd.y][nd.x] != 0) {
                M--;
                continue;
            }
            sharkNow[nd.y][nd.x] = nd.sharkN;
            sharkSmell[nd.y][nd.x] = new node(K, nd.sharkN);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열크기
        M = Integer.parseInt(st.nextToken()); // 상어 숫자
        K = Integer.parseInt(st.nextToken()); // 냄새 지속시간

        sharkNow = new int[N][N];
        sharkDir = new int[M + 1][4][4];
        sharkSmell = new node[N][N]; // 냄새, 냄새 주인 담는 객체
        nowSharkWatch = new int[M + 1];
        // 냄새가 = 0 이라면, 해당 칸을 비워야함...
        // sharkNow은 상어의 현재위치 계속 트래킹해야함
        // sharkDir은 상어가 바라보는 위치 별 우선순위 방향 담는 3차원 배열
        // sharkDir[상어 번호][현재 상어가 바라보는 방향][방향 별 우선순위]

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sharkSmell[i][j] = new node(0, 0);
                sharkNow[i][j] = Integer.parseInt(st.nextToken());
                if (sharkNow[i][j] > 0) {
                    sharkSmell[i][j] = new node(K, sharkNow[i][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            nowSharkWatch[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharkDir[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int time = 0;
        while (time < 1000) {
            time++;
            sharkMover();
            smellRemover();
            if (M == 1) {
                System.out.println(time);
                System.exit(0);
            }
        }

        System.out.println(-1);
    }
}