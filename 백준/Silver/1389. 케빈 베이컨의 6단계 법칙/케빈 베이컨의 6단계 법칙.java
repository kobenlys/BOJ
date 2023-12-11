import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M, flag, INF = 10001;
    public static int[][] arr1;

    public static void floydW() { // 구현

        // 플로이드 워셜 알고리즘
        for (int fw = 1; fw <= N; fw++) { // 거쳐가는 친구
            for (int i = 1; i <= N; i++) { // 출발 친구
                for (int j = 1; j <= N; j++) { // 목표 친구

                    // i -> j (i,j 가 직접 친구) 와 i -> fw -> j (i,j 가 공통으로 있는친구)
                    // 중 가장 가까운 연결단계 입력
                    arr1[i][j] = Math.min(arr1[i][j], arr1[i][fw] + arr1[fw][j]);
                }
            }
        }

        // 케빈 베이컨 법칙 최솟값 노드 출력
        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                cnt += arr1[i][j];
            }
            if (ans > cnt) {
                ans = cnt;
                flag = i;
            }
        }
    }

    public static void main(String[] args) throws IOException { // 값 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr1 = new int[N + 1][N + 1];
        // 자기 자신 제외 INF로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(arr1[i], INF);
            arr1[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // A -> B 가 친구라면  B -> A 도 친구이다, (양방향)
            arr1[s][e] = arr1[e][s] = 1;
        }
        // 함수 호출, 출력
        floydW();
        System.out.print(flag);
    }
}