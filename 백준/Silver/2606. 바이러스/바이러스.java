import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[][] arr1;

    // 플로이드 워셜 알고리즘
    public static void floydWarshall() {

        for (int fw = 1; fw <= N; fw++) { // fw 노드를 기준으로
            for (int i = 1; i <= N; i++) { // i 에서
                for (int j = 1; j <= N; j++) { // j로 가는것이 가능한지?
                    if (i == j) continue;

                    // i -> fw -> j
                    // 즉 i -> j로 갈때 fw를 거쳐서 갈 수 있는지 판단
                    if (arr1[i][fw] && arr1[fw][j]) {
                        arr1[i][j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        arr1 = new boolean[N + 1][N + 1];

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr1[s][e] = arr1[e][s] = true;
        }

        floydWarshall();
        int answer = 0;

        // 1 -> i 갈 수 있다면 개수 세기
        for (int i = 2; i <= N; i++) {
            if (arr1[1][i]) answer++;
        }
        System.out.println(answer);
    }
}