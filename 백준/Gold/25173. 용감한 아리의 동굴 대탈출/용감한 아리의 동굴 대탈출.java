import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int ahriHP, ahriDamage, bossHP, bossDamage;
    public static int[][] arr1;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    
    // 아리, 보스 정보를 담는 클래스
    public static class Player {
        int nowX, nowY, beforeX, beforeY, dir;

        public Player(int nowX, int nowY, int beforeX, int beforeY, int dir) {
            this.nowX = nowX;
            this.nowY = nowY;
            this.beforeX = beforeX;
            this.beforeY = beforeY;
            this.dir = dir;
        }
    }
    
    // 보스의 부하 정보를 담는 클래스
    public static class Monster {
        int x, y, hp;

        public Monster(int x, int y, int hp) {
            this.x = x;
            this.y = y;
            this.hp = hp;
        }
    }

    // 범위 체크
    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    // 아리 위치 찾기.
    public static int findDirection(Player boss) {

        for (int i = 0; i < 4; i++) {
            int nx = boss.nowX + dx[i];
            int ny = boss.nowY + dy[i];
            if (!isRange(nx, ny)) continue;

            if (arr1[ny][nx] == 2) {
                return i;
            }
        }
        return -1;
    }

    // 아리 움직이기.
    public static boolean ahriMove(Player ahri) {

        for (int i = ahri.dir; i <= ahri.dir + 4; i++) {
            int nx = ahri.nowX + dx[i % 4];
            int ny = ahri.nowY + dy[i % 4];

            // 아리가 방향 변경할때마다 체력 -1
            if (i != ahri.dir) {
                ahriHP--;
            }
            if (!isRange(nx, ny)) continue;

            if (arr1[ny][nx] == 0) {
                // 아리 위치 갱신 및 이전위치 또한 갱신
                arr1[ahri.nowY][ahri.nowX] = 0;
                arr1[ny][nx] = 2;
                ahri.beforeX = ahri.nowX;
                ahri.beforeY = ahri.nowY;
                ahri.nowX = nx;
                ahri.nowY = ny;
                ahri.dir = i % 4;
                return true;
            }
        }
        return false;
    }

    public static void bossAttack(Player boss) {
        int len = 0;
        int x = boss.nowX;
        int y = boss.nowY;
        int dir = boss.dir;
        Monster spawn = new Monster(-1, -1, bossDamage);

        // 보스 몬스터중심으로 달팽이패턴으로 석순 탐색
        while (len <= N + M) {

            // 달팽이 패턴이며, 시계방향으로 석순 탐색
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 2) {
                    len++;
                }

                for (int k = 0; k < len; k++) {
                    x = x + dx[(dir + i) % 4];
                    y = y + dy[(dir + i) % 4];
                    if (!isRange(x, y)) continue;
                    if (arr1[y][x] == 1) { // 석순 발견시
                        spawn.x = x;
                        spawn.y = y;
                        findAhri(spawn); // 몬스터가 아리 공격하기.
                        return;
                    }
                }
            }
        }
    }

    // 몬스터가 아리공격
    public static void findAhri(Monster ms) {
        Queue<Monster> qu = new ArrayDeque<>();
        boolean[][] vi = new boolean[N][M];
        qu.offer(ms);

        while (!qu.isEmpty()) {
            Monster nd = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (!isRange(nx, ny)) continue;

                if (!vi[ny][nx] && arr1[ny][nx] != 1 && arr1[ny][nx] != 3) {
                    vi[ny][nx] = true;

                    // 아리 발견시 공격.
                    if (arr1[ny][nx] == 2) {
                        ahriHP -= nd.hp - 1;
                        return;
                    }
                    // 몬스터 hp = 0일때 제거
                    if (nd.hp - 1 <= 0) continue;
                    qu.offer(new Monster(nx, ny, nd.hp - 1));
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
        arr1 = new int[N][M];

        // 아리와 보스 위치, 이전위치, 방향 정보 관리하기.
        Player ahri = new Player(0, 0, 0, 0, 0);
        Player boss = new Player(0, 0, -1, -1, 0);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                if (arr1[i][j] == 2) {
                    ahri.nowX = j;
                    ahri.nowY = i;
                    ahri.beforeX = j;
                    ahri.beforeY = i;
                }
                if (arr1[i][j] == 3) {
                    boss.nowX = j;
                    boss.nowY = i;
                }
            }
        }

        // 아리와 보스 체력, 공격력 담기
        st = new StringTokenizer(br.readLine());
        ahriHP = Integer.parseInt(st.nextToken());
        ahriDamage = Integer.parseInt(st.nextToken());
        bossHP = Integer.parseInt(st.nextToken());
        bossDamage = Integer.parseInt(st.nextToken());

        int direction = findDirection(boss); // 최초 위치 설정
        String result = "CAVELIFE...";

        ahri.dir = direction;
        boss.dir = direction;

        while (true) {
            // 아리 공격
            bossHP -= ahriDamage;
            if (bossHP <= 0) {
                result = "VICTORY!";
                break;
            }
            // 아리 이동
            boolean isUpdate = ahriMove(ahri);

            // 보스 공격
            bossAttack(boss);
            if (ahriHP <= 0) {
                break;
            }
            // 보스 이동
            if (!isUpdate) continue;
            arr1[boss.nowY][boss.nowX] = 0;
            boss.nowX = ahri.beforeX;
            boss.nowY = ahri.beforeY;
            arr1[boss.nowY][boss.nowX] = 3; // 보스 위치 변환
            boss.dir = ahri.dir;
        }
        System.out.println(result);
    }
}