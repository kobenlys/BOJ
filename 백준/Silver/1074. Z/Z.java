import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, r, c, cnt;


    public static void algorithm(int size, int x, int y) {
        if (size == 2) {
            calc(x, y);
            return;
        }

        int nSize = size / 2;
        // 분할정복 + 재귀만 이용하면 시간초과가 난다
        // 그렇다면 1~4분면 을 다 탐색 하지 않고 8*8 배열의 중간값 4,4를 기준으로 1~4분면을 나눠야 한다.
        // 즉 이진탐색기법을 사용해서 배열의 사이즈마다 중간 값을 구하고 r,c 가 어떤 분면에 속하는지에 따라 재귀한다.
        if (r < y + nSize && c < x + nSize) { // 제 2사분면, 왼쪽 위
            algorithm(nSize, x, y);
        } else if (r < y + nSize && c >= x + nSize) { //제 1사분면, 오른쪽 위
            cnt += nSize * nSize;
            algorithm(nSize, x + nSize, y);
        } else if (r >= y + nSize && c < x + nSize) { //제 3사분면, 왼쪽 아래
            cnt += nSize * nSize * 2;
            algorithm(nSize, x, y + nSize);
        } else {//제 4사분면, 오른쪽 아래
            cnt += nSize * nSize * 3;
            algorithm(nSize, x + nSize, y + nSize);
        }
    }

    public static void calc(int x, int y) {
        int[] dx = {0, 1, 0, 1};
        int[] dy = {0, 0, 1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx == c && ny == r) {
                // Z로 움직인 만큼 i를 더해준다
                System.out.println(cnt + i);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2, N);

        algorithm(size, 0, 0);
    }
}