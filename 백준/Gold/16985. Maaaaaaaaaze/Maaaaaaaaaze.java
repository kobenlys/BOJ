import java.io.*;
import java.util.*;

public class Main {
    public static int cnt = 1;
    public static int[][][] arr1;
    public static boolean[][][] vi;
    private static boolean[] visit;
    public static HashSet<String> set = new HashSet<>();
    public static HashSet<String> set2 = new HashSet<>();

    public static class node {
        int z, x, y, cnt;

        public node(int z, int x, int y, int cnt) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void dfs1(String input) {

        if (set.contains(input)) return;

        if (input.length() == 5) {
            set.add(input);
            return;
        }

        for (int i = 0; i < 4; i++) {
            dfs1(input + i);
        }
    }

    public static void dfs2(String input) {

        if (set2.contains(input)) return;

        if (input.length() == 5) {
            set2.add(input);
            return;
        }

        for (int i = 0; i <= 4; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs2(input + i);
                visit[i] = false;
            }
        }
    }

    public static int[][][] controlStack(String dir, int[][][] copy) {
        int[][][] tmp = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            int toIdx = Character.getNumericValue(dir.charAt(i));
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    tmp[toIdx][j][k] = copy[i][j][k];
                }
            }
        }
        return tmp;
    }

    public static int[][][] controlCube(String dir) {
        int[][][] tmp = new int[5][5][5];
        // 0은 제자리, 1은 90, 2은 180, 3은 270


        for (int i = 0; i < 5; i++) {
            char control = dir.charAt(i);

            if (control == '0') { // 원위치
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmp[i][j][k] = arr1[i][j][k];
                    }
                }
            }

            if (control == '1') { // 90도 회전
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmp[i][k][4 - j] = arr1[i][j][k];
                    }
                }
            }

            if (control == '2') { // 180도 회전
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmp[i][4 - j][4 - k] = arr1[i][j][k];
                    }
                }
            }

            if (control == '3') { // 270도 회전
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        tmp[i][4 - k][j] = arr1[i][j][k];
                    }
                }
            }
        }
        return tmp;
    }

    public static int bfs(int[][][] now) {
        Queue<node> qu = new LinkedList<>();
        int[] dx = {0, 0, -1, 1, 0, 0};
        int[] dy = {-1, 1, 0, 0, 0, 0};
        int[] dz = {0, 0, 0, 0, -1, 1};

        qu.offer(new node(0, 0, 0, 0));

        if (now[0][0][0] == 0 || now[4][4][4] == 0) {
            return -1;
        }

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (nd.z == 4 && nd.y == 4 && nd.x == 4) {
                return nd.cnt;
            }

            for (int i = 0; i < 6; i++) {
                int nz = nd.z + dz[i];
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];
                if (nz < 0 || nx < 0 || ny < 0 || nz >= 5 || nx >= 5 || ny >= 5) continue;

                if (!vi[nz][ny][nx] && now[nz][ny][nx] == 1) {
                    vi[nz][ny][nx] = true;
                    qu.offer(new node(nz, nx, ny, nd.cnt + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr1 = new int[5][5][5];
        visit = new boolean[5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    arr1[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        dfs1("");
        dfs2("");

        int answer = Integer.MAX_VALUE;
        for (String dir : set) {
            vi = new boolean[5][5][5];
            int[][][] cube = controlCube(dir);

            for (String dir2 : set2) {
                vi = new boolean[5][5][5];
                int[][][] cubeStack = controlStack(dir2, cube);
                int res = bfs(cubeStack);
                if (res == -1) continue;
                answer = Math.min(answer, res);
            }
        }
        
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
