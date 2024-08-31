import java.io.*;
import java.util.*;

public class Solution {
    public static int N, max, answer;
    public static int[][] arr1;
    public static List<Node> list;

    public static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean[][] deepCopy(boolean[][] origin) {
        boolean[][] copy = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = origin[i][j];
            }
        }
        return copy;
    }

    public static boolean connectLeft(int x, int y, boolean[][] vi) {
        for (int i = 1; i < N; i++) {
            int nx = x + -1 * i;
            if (nx < 0) return true;
            if (vi[y][nx] || arr1[y][nx] == 1) return false;
        }
        return true;
    }

    public static boolean connectRight(int x, int y, boolean[][] vi) {
        for (int i = 1; i < N; i++) {
            int nx = x + i;
            if (nx >= N) return true;
            if (vi[y][nx] || arr1[y][nx] == 1) return false;
        }
        return true;
    }

    public static boolean connectUp(int x, int y, boolean[][] vi) {
        for (int i = 1; i < N; i++) {
            int ny = y + -1 * i;
            if (ny < 0) return true;
            if (vi[ny][x] || arr1[ny][x] == 1) return false;
        }
        return true;
    }

    public static boolean connectDown(int x, int y, boolean[][] vi) {
        for (int i = 1; i < N; i++) {
            int ny = y + i;
            if (ny >= N) return true;
            if (vi[ny][x] || arr1[ny][x] == 1) return false;
        }
        return true;
    }

    public static int checkLength(boolean[][] vi) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (vi[i][j]) cnt++;
            }
        }
        return cnt;
    }

    public static void dfs(int idx, int cnt, boolean[][] vi) {

        if (idx == list.size()) {
            if (max <= cnt) {
                if (max < cnt) {
                    max = cnt;
                    answer = Integer.MAX_VALUE;
                }
                answer = Math.min(answer, checkLength(vi));
            }
            return;
        }

        Node nd = list.get(idx);
        int x = nd.x;
        int y = nd.y;


        if (connectLeft(x, y, vi)) {
            for (int i = 0; i < x; i++) {
                vi[y][i] = true;
            }
            dfs(idx + 1, cnt + 1, vi);
            for (int i = 0; i < x; i++) {
                vi[y][i] = false;
            }
        }

        if (connectRight(x, y, vi)) {
            for (int i = x + 1; i < N; i++) {
                vi[y][i] = true;
            }
            dfs(idx + 1, cnt + 1, vi);
            for (int i = x + 1; i < N; i++) {
                vi[y][i] = false;
            }
        }

        if (connectUp(x, y, vi)) {
            for (int i = y - 1; i >= 0; i--) {
                vi[i][x] = true;
            }
            dfs(idx + 1, cnt + 1, vi);
            for (int i = y - 1; i >= 0; i--) {
                vi[i][x] = false;
            }
        }

        if (connectDown(x, y, vi)) {
            for (int i = y + 1; i < N; i++) {
                vi[i][x] = true;
            }
            dfs(idx + 1, cnt + 1, vi);
            for (int i = y + 1; i < N; i++) {
                vi[i][x] = false;
            }
        }

        dfs(idx + 1, cnt, vi);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int processor = 0;
            answer = Integer.MAX_VALUE;
            max = 0;

            arr1 = new int[N][N];
            list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Integer.parseInt(st.nextToken());

                    if (arr1[i][j] == 1 && (j == 0 || i == 0)) {
                        continue;
                    }
                    if (arr1[i][j] == 1) {
                        list.add(new Node(j, i));
                    }
                }
            }

            dfs(0, 0, new boolean[N][N]);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}