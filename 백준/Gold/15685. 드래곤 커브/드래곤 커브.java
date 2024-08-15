import java.io.*;
import java.util.*;

public class Main {
    public static boolean[][] arr1;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static int findAnswer() {
        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr1[i][j] && arr1[i][j + 1] && arr1[i + 1][j] && arr1[i + 1][j + 1]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void dragonCurve(int x, int y, int d, int round) {

        ArrayList<Integer> list = new ArrayList<>();        
        arr1[y][x] = true;
        x = x + dx[d];
        y = y + dy[d];
        arr1[y][x] = true;
        
        list.add(d);

        while (round-- > 0) {

            int nowSize = list.size();
            for (int i = nowSize - 1; i >= 0; i--) {
                int dir = list.get(i);

                if (dir == 0) {
                    dir = 1;
                } else if (dir == 1) {
                    dir = 2;
                } else if (dir == 2) {
                    dir = 3;
                } else if (dir == 3) {
                    dir = 0;
                }

                x = x + dx[dir];
                y = y + dy[dir];
                arr1[y][x] = true;
                list.add(dir);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr1 = new boolean[101][101];

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int round = Integer.parseInt(st.nextToken());

            dragonCurve(x, y, dir, round);
        }

        int answer = findAnswer();
        System.out.println(answer);
    }
}