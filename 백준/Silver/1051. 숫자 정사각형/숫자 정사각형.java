import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, max;
    public static int[][] arr1;

    public static void algorithm() {
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int a = arr1[i][j]; // 좌측 상단 꼭짓점 기준
                int cnt = 0;
                for (int k = j; k < M; k++, cnt++) { // 해당 좌표 에서 M-1 까지 탐색
                    if (range(j, i, cnt)) {
                        // cnt 통해 1*1 정사각형 부터 탐색
                        int b = arr1[i][j + cnt]; // 우측 상단 꼭짓점
                        int c = arr1[i + cnt][j]; // 좌측 하단 꼭짓점
                        int d = arr1[i + cnt][j + cnt]; // 우측 하단 꼭짓점

                        if (a == b && a == c && a == d) {
                            max = Math.max(max, cnt);
                        }
                    }
                }
            }
        }
    }

    public static boolean range(int x, int y, int size) { // 좌표 범위 체크
        return x + size >= 0 && y + size >= 0 && x + size < M && y + size < N;
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        // 함수 실행, 정사각형 크기 출력
        algorithm();
        System.out.print(++max * max);
    }
}
