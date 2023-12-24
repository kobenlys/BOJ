import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, Y, X, cnt;

    public static void algorithm(int x, int y, int size) {

        if (size == 2) {
            calc(x, y);
            return;
        }

        int nSize = size / 2;

        if (X < x + nSize && Y < y + nSize) {
            algorithm(x, y, nSize);
        } else if (X >= x + nSize && Y < y + nSize) {
            cnt += nSize * nSize;
            algorithm(x + nSize, y, nSize);
        } else if (X < x + nSize && Y >= y + nSize) {
            cnt += nSize * nSize * 2;
            algorithm(x, y + nSize, nSize);
        } else {
            cnt += nSize * nSize * 3;
            algorithm(x + nSize, y + nSize, nSize);
        }
    }

    public static void calc(int x, int y) {
        int[] dx = {0, 1, 0, 1};
        int[] dy = {0, 0, 1, 1};


        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx == X && ny == Y) {
                System.out.println(cnt + i);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int newSize = (int) Math.pow(2, N);
        algorithm(0, 0, newSize);
    }
}