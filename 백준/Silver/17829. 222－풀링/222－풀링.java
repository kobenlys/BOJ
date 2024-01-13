import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;

    public static int algorithm(int size, int x, int y) {

        if (size == 2) { // 나눌수 있는 최소 단위라면 두번째 큰값 리턴
            int[] res = new int[4];
            int idx = 0;
            for (int i = y; i < y + 2; i++) {
                for (int j = x; j < x + 2; j++) {
                    res[idx++] = arr1[i][j];
                }
            }
            Arrays.sort(res);
            return res[2];
        } else { // 각각 8,4,2 일때 2번째로 큰값 받는다.
            int[] res = new int[4];
            // 원래 size가 8 일때
            int nSize = size / 2; // size = 4로 분할 후
            res[0] = algorithm(nSize, x, y); // 1분면
            res[1] = algorithm(nSize, x + nSize, y); // 2분면
            res[2] = algorithm(nSize, x, y + nSize); // 3분면
            res[3] = algorithm(nSize, x + nSize, y + nSize); // 4분면
            // 깊게 들어간다면 4 -> 2로 분할 후 리턴.
            Arrays.sort(res);
            return res[2];
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(algorithm(N, 0, 0));
    }
}
