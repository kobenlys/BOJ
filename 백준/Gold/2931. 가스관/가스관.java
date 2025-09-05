import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static char[][] arr1;
    public static Cache cache;
    public static Map<Character, List<Integer>> roadDirection;
    public static boolean[][] vi;
    public static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
    public static int[] dy = {-1, 1, 0, 0};

    public static class Cache {

        int x, y;
        char road;

        public Cache(int x, int y, char road) {
            this.x = x;
            this.y = y;
            this.road = road;
        }

        public void printCache() {
            System.out.println(y + " " + x + " " + road);
        }
    }

    public static boolean isIn(int x, int y) {
        return 0 <= x && 0 <= y && x < M && y < N;
    }

    public static int[] findRoad(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isIn(nx, ny) && roadDirection.containsKey(arr1[ny][nx])) {
                return new int[]{nx, ny};
            }
        }
        return null;
    }

    public static char findPipe(int x, int y) {

        boolean allowedUp = false;
        boolean allowedDown = false;
        boolean allowedLeft = false;
        boolean allowedRight = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isIn(nx, ny) && roadDirection.containsKey(arr1[ny][nx])) {
                List<Integer> list = roadDirection.get(arr1[ny][nx]);
                if (i == 0) {
                    if (list.contains(1)) {
                        allowedUp = true;
                    }
                } else if (i == 1) {
                    if (list.contains(0)) {
                        allowedDown = true;
                    }
                } else if (i == 2) {
                    if (list.contains(3)) {
                        allowedLeft = true;
                    }
                } else {
                    if (list.contains(2)) {
                        allowedRight = true;
                    }
                }
            }
        }

        if (allowedUp && allowedDown && allowedLeft && allowedRight) {
            return '+';
        } else if (allowedUp && allowedDown) {
            return '|';
        } else if (allowedLeft && allowedRight) {
            return '-';
        } else if (allowedDown && allowedRight) {
            return '1';
        } else if (allowedUp && allowedRight) {
            return '2';
        } else if (allowedUp && allowedLeft) {
            return '3';
        } else {
            return '4';
        }
    }

    public static void dfs(int x, int y) {

        if (arr1[y][x] == 'Z') {
            cache.printCache();
            System.exit(0);
        }

        if (!roadDirection.containsKey(arr1[y][x])) {
            // 가스관 놔야함
            char selectedPipe = findPipe(x, y);
            arr1[y][x] = selectedPipe;
            cache.road = selectedPipe;
            cache.x = x + 1;
            cache.y = y + 1;
        }

        List<Integer> list = roadDirection.get(arr1[y][x]);
        for (int idx : list) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            if (isIn(nx, ny) && !vi[ny][nx]) {
                vi[ny][nx] = true;
                dfs(nx, ny);
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
        vi = new boolean[N][M];
        cache = new Cache(0, 0, '-');
        roadDirection = new HashMap<>();
        roadDirection.put('|', Arrays.asList(0, 1));
        roadDirection.put('-', Arrays.asList(2, 3));
        roadDirection.put('+', Arrays.asList(0, 1, 2, 3));
        roadDirection.put('1', Arrays.asList(1, 3));
        roadDirection.put('2', Arrays.asList(0, 3));
        roadDirection.put('3', Arrays.asList(0, 2));
        roadDirection.put('4', Arrays.asList(1, 2));

        int x = 0, y = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = str.charAt(j);
                if (arr1[i][j] == 'M') {
                    vi[i][j] = true;
                    x = j;
                    y = i;
                }
            }
        }

        int[] startPosition = findRoad(x, y);
        dfs(startPosition[0], startPosition[1]);
    }
}