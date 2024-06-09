import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;

    public static class node {
        int x, y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean findMine(int x, int y) {
        // 상 하 좌 우 좌상각 우상각 좌하각 우하각
        Stack<node> stk = new Stack<>();
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};


        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (arr1[ny][nx] == 0) return false;
            if (arr1[ny][nx] != -1) {
                stk.push(new node(nx, ny));
            }
        }

        while (!stk.isEmpty()) {
            int tmpX = stk.peek().x;
            int tmpY = stk.pop().y;
            arr1[tmpY][tmpX]--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            arr1 = new int[N][N];
            int answer = 0;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    char n = input.charAt(j);
                    if (Character.isDigit(n)) {
                        arr1[i][j] = Character.getNumericValue(n);
                    } else {
                        arr1[i][j] = -1;
                    }
                }
            }

            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < N - 1; j++) {
                    if (findMine(j, i)) {
                        answer++;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}