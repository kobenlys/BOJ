import java.io.*;
import java.util.*;

public class Main {
    public static int res;
    public static char[][] arr1;
    public static boolean[][] vi;
    public static List<Integer> list = new ArrayList<>();

    public static boolean isConnect(int idx) {

        Queue<Integer> qu = new ArrayDeque<>();
        int cnt = 1;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        qu.offer(idx);
        vi[idx / 5][idx % 5] = false;

        while (!qu.isEmpty()) {

            int pos = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos % 5 + dx[i];
                int ny = pos / 5 + dy[i];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;

                if (vi[ny][nx]) {
                    cnt++;
                    vi[ny][nx] = false;
                    qu.offer(ny * 5 + nx);
                }
            }
        }
        return cnt == 7;
    }

    public static void dfs(int start, int idx) {

        if (start == 7) {

            int cnt = 0;
            vi = new boolean[5][5];

            for (int e : list) {
                int y = e / 5;
                int x = e % 5;
                vi[y][x] = true;
                if (arr1[y][x] == 'S') cnt++;
            }

            if (cnt >= 4 && isConnect(list.get(0))) {
                res++;
            }
            return;
        }

        for (int i = idx; i < 25; i++) {
            list.add(i);
            dfs(start + 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr1 = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr1[i][j] = input.charAt(j);
            }
        }

        dfs(0, 0);
        System.out.println(res);
    }
}