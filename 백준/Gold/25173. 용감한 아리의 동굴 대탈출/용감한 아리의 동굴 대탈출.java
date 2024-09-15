import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static int ahriHP, ahriDamage, bossHP, bossDamage;
    public static int[][] arr1;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    // 1은 석순, 2는 아리 시작, 3은 보스 위치, 0은 빈공간

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

    public static class Monster {
        int x, y, hp;

        public Monster(int x, int y, int hp) {
            this.x = x;
            this.y = y;
            this.hp = hp;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }

    public static int findDirection(Player boss) {

        // 보스 기준으로 ㄱㄱ
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

    public static boolean ahriMove(Player ahri) {

        for (int i = ahri.dir; i <= ahri.dir + 4; i++) {
            int nx = ahri.nowX + dx[i % 4];
            int ny = ahri.nowY + dy[i % 4];
            if(i != ahri.dir){
                ahriHP--;
            }
            if (!isRange(nx, ny)) continue;

            if (arr1[ny][nx] == 0) {
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
        boolean isSpawn = false;

        boolean[][] vi = new boolean[N][M];
        vi[y][x] = true;

        st:
        while (len <= Math.max(N, M)*2) {
            len++;

            for (int i = 0; i < len; i++) {
                x = x + dx[dir];
                y = y + dy[dir];
                if (!isRange(x, y)) continue;
                vi[y][x] = true;
                if (arr1[y][x] == 1) {
                    isSpawn = true;
                    break st;
                }
            }
            dir = (dir + 1) % 4;
            for (int i = 0; i < len; i++) {
                x = x + dx[dir];
                y = y + dy[dir];
                if (!isRange(x, y)) continue;
                vi[y][x] = true;
                if (arr1[y][x] == 1) {
                    isSpawn = true;
                    break st;
                }
            }
            dir = (dir + 1) % 4;
            len++;

            for (int i = 0; i < len; i++) {
                x = x + dx[dir];
                y = y + dy[dir];
                if (!isRange(x, y)) continue;
                vi[y][x] = true;
                if (arr1[y][x] == 1) {
                    isSpawn = true;
                    break st;
                }
            }
            dir = (dir + 1) % 4;
            for (int i = 0; i < len; i++) {
                x = x + dx[dir];
                y = y + dy[dir];
                if (!isRange(x, y)) continue;
                vi[y][x] = true;
                if (arr1[y][x] == 1) {
                    isSpawn = true;
                    break st;
                }
            }

            dir = (dir + 1) % 4;
        }


        if (isSpawn) {
            spawn.x = x;
            spawn.y = y;
            findAhri(spawn);
        }
    }

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

                    if (arr1[ny][nx] == 2) {
                        ahriHP -= nd.hp - 1;
                        return;
                    }
                    if(nd.hp-1 <= 0) continue;
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

        // 아리는 동굴안 격자판중 한칸에, 보스는 아리와 상하좌우 인접한 칸중 한칸에 인접하며 전투시작
        // 전투가 시작될때 보스의 진행 방향은 아리를 바라보고 있는 방향, 아리또한 처음엔 같음
        // 아리 보스 들다 석순, 벽 통과 ㄴㄴ 동시에 같은 한칸 ㄴㄴ

        // 워크 프로세스
        // 아리 공격 -> 아리 이동 -> 보스 공격 -> 보스 이동
        // 둘중 한명이 공격으로인해 체력이 0이라면 패배, 시스템 종료

        // 아리는 공격 할때 D만큼 한번 공격 가능 하고, 현재 진행 방향으로 한칸 이동
        // -> 이동 불가시, 진행방향 찾을때 까지 오른쪽으로 90도 씩 회전, 회전시 체력 -1 소모 (4번 한계가있음)
        // -> 이동 가능한 방향 찾았다면 한칸 이동

        //보스 공격차례일때
        // 석순을 하나 찾아, 부하 몬스터 한마리를 석순 위치에 소환, 석순을 발견하는 순간 과정을 멈추고, 몬스터 소환
        // 보스는 바라보는 방향에 따라 달팽이 식으로 배열 회전하며 찾음, 각각 다름

        // 석순 발견시, 보스 공격력의 E만큼 체력을 가지는 부하 몬스터 한마리 소환
        // 부하 몬스터는 최단거리로 이동하여 아리를 공격하고 사라짐, 부하몬스터가 사라져야만 보스의 공격차례가 끝남
        // 부하 몬스터는 상하 좌우로 이동하며 이동할때마다 체력을 1소모, 남은 체력만큼 아리에게 데미지를 입힘

        // 보스는 아리가 마지막으로 이동하기 전 위치칸으로 이동하고, 아리가 마지막으로 이동하고 난 후의 진행 방향과 같은 방향을 가짐
        // 만약 아리랑 겹친다면 보스도 이동하지 않는다.

        arr1 = new int[N][M];

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

        st = new StringTokenizer(br.readLine());
        ahriHP = Integer.parseInt(st.nextToken());
        ahriDamage = Integer.parseInt(st.nextToken());
        bossHP = Integer.parseInt(st.nextToken());
        bossDamage = Integer.parseInt(st.nextToken());

        int direction = findDirection(boss);
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
            arr1[boss.nowY][boss.nowX] = 3;
            boss.dir = ahri.dir;
        }

        System.out.println(result);
    }
}
