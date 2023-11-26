import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] arr1 = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 거쳐가는 정점 -> 플로이드 워셜 알고리즘은 거쳐가는 정점을 기준으로 계산한다.
        for (int fw = 0; fw < n; fw++) { // 플로이드 워셜 알고리즘
            // 출발 정점
            for (int i = 0; i < n; i++) {
                // 도착 장
                for (int j = 0; j < n; j++) {
                    // i - > j 로 갈 수 있는 경로가 있는지 판단한다.
                    // i -> fw -> j 도 성립한다. 문제 해결위해 플로이드 워셜 을 사용한다.
                    if (arr1[i][fw] == 1 && arr1[fw][j] == 1) {
                        arr1[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
