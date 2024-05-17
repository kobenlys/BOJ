import java.io.*;
import java.util.*;

public class Solution {
    public static String[][] arr1 = new String[4][4];
    public static HashSet<String> set;

    public static class node {
        int x, y, cnt;
        String number;

        public node(int x, int y, int cnt, String number) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.number = number;
        }
    }

    public static void bfs(int x, int y) {
        Queue<node> qu = new LinkedList<>();
        qu.offer(new node(x, y, 1, arr1[y][x]));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!qu.isEmpty()) {

            node nd = qu.poll();

            if (nd.cnt == 7) {
                set.add(nd.number);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nd.x + dx[i];
                int ny = nd.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
                String str = nd.number.concat(arr1[ny][nx]);
                qu.offer(new node(nx, ny, nd.cnt + 1, str));

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            set = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    arr1[i][j] = st.nextToken();
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    bfs(j, i);
                }
            }

            sb.append("#").append(tc).append(" ");
            sb.append(set.size());
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
}