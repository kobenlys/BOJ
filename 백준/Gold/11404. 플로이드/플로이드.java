import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static int[][] arr1;

    public static void algorithm() { // 플로이드 워셜 알고리즘
        // fw = 거쳐 가는 노드
        for (int fw = 0; fw < N; fw++) {
            // i = 출발 노드
            for (int i = 0; i < N; i++) {
                // j = 도착 노드
                for (int j = 0; j < N; j++) {
                    // i -> j 와 i -> fw -> j 두 경우중 가장 작은 비용 입력.
                    arr1[i][j] = Math.min(arr1[i][j], arr1[i][fw] + arr1[fw][j]);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                // 갈 수 있는 경로가 없다면 MAX 값 입력, 미리 채워 넣는다.
                // 조건1. 비용은 100,000보다 작거나 같은 자연수 이다.
                // 배열이 100*100 일때 최대 비용 100,000 * 100 이다.
                arr1[i][j] = 10000001;
            }
        }

        // 갈 수 있는 경로 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1; // 좌표
            int x = Integer.parseInt(st.nextToken()) - 1;
            int price = Integer.parseInt(st.nextToken()); // 가격

            // 동일한 경로 존재 시 가장 적은 비용을 입력.
            arr1[y][x] = Math.min(arr1[y][x], price);
        }


        // 플로이드 워셜 알고리즘 호출.
        algorithm();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr1[i][j] == 10000001){
                    arr1[i][j] = 0;
                }
                sb.append(arr1[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}


