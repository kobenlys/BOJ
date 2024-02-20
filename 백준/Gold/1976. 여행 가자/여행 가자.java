import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;

    public static void floydWarshall() {
        // 플로이드워셜 알고리즘. 시간 복잡도 : O(V^3)
        for (int fw = 0; fw < N; fw++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (arr1[i][fw] == 1 && arr1[fw][j] == 1) {
                        arr1[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int[] plan = new int[M];
        arr1 = new int[200][200];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 플로이드워셜 알고리즘 실행.
        floydWarshall();

        // 여행 플랜에 맞는 도시가 갈 수있는지 확인.
        for (int i = 0; i < M - 1; i++) {
            if (plan[i] == plan[i + 1]) continue;
            if (arr1[plan[i]][plan[i + 1]] == 0) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}