import java.io.*;
import java.util.*;

public class Main {

    public static char[][] arr1;
    public static int minPinCnt, move;
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0};

    public static void calcAnswer(int resMove, int resPinCnt) {

        if (minPinCnt >= resPinCnt) {
            if (minPinCnt == resPinCnt) {
                move = Math.min(move, resMove);
                return;
            }
            minPinCnt = resPinCnt;
            move = resMove;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 9 && y < 5;
    }

    public static void dfs(int move, int pinCount) {

        calcAnswer(move, pinCount);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (arr1[i][j] == 'o') {

                    for (int k = 0; k < 4; k++) {
                        int nx = j + dx[k];
                        int ny = i + dy[k];
                        int nnx = j + dx[k] * 2;
                        int nny = i + dy[k] * 2;
                        if (isRange(nx, ny) && isRange(nnx, nny)) {
                            if (arr1[ny][nx] == 'o' && arr1[nny][nnx] == '.') {
                                arr1[i][j] = arr1[ny][nx] = '.';
                                arr1[nny][nnx] = 'o';
                                dfs(move + 1, pinCount - 1);
                                arr1[i][j] = arr1[ny][nx] = 'o';
                                arr1[nny][nnx] = '.';
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            arr1 = new char[5][9];
            int pin = 0;
            move = 0;

            for (int i = 0; i < 5; i++) {
                String str = br.readLine();
                for (int j = 0; j < 9; j++) {
                    arr1[i][j] = str.charAt(j);
                    if (arr1[i][j] == 'o') {
                        pin++;
                    }
                }
            }
            minPinCnt = pin;

            if (T != 0) {
                br.readLine();
            }

            dfs(0, pin);
            sb.append(minPinCnt).append(" ").append(move).append("\n");
        }

        System.out.println(sb);
    }
}