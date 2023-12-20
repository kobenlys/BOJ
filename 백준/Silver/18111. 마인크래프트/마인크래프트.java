import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1;
    public static int N, M, B, tmp, start, end, min = Integer.MAX_VALUE;


    public static void algorithm() {
        // "땅의 높이는 256작거나 같은 자연수 또는 0 이다."
        while (start <= end) {
            int inv = B;
            int t1 = 0, t2 = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int ground = arr1[i][j];
                    // T로 평탄화 시키기 위해 필요한 작업 횟수
                    ground = ground - start;
                    int elements = Math.abs(ground);
                    // T로 평탄화 하기 위해선?
                    if (ground > 0) { // 1번 작업 -> 블록제거 + 인벤 + 필요없는 만큼
                        t1 += elements * 2;
                        inv += elements;
                    } else if (ground < 0) { // 2번 작업 -> 블록설치 + 인벤 - 필요한만큼
                        t2 += elements;
                        inv -= elements;
                    }
                }
            }

            if (inv >= 0) { // T로 평탄화 시키기 위해서 인벤토리에 있는 요소 이상으로 필요하다면 평탄화 불가
                if (min >= t1 + t2) {
                    min = t1 + t2;
                    tmp = start;
                }
            }
            start++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr1 = new int[N][M];

        start = 257;
        end = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
                // 최댓값 이상으로 검색 할 필요 없음
                end = Math.max(end, arr1[i][j]);
                // 최솟값 부터 검색하면 된다.
                start = Math.min(start, arr1[i][j]);
            }
        }
        algorithm();
        System.out.println(min + " " + tmp);
    }
}