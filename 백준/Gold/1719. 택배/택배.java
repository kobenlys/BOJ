import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, INF = 20_000_001;
    public static int[][] arr1;
    public static int[][] trace;

    public static void floydWarshall() {
        // 플로이드워셜 알고리즘 실행.
        for (int fw = 0; fw < N; fw++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (arr1[i][j] > arr1[i][fw] + arr1[fw][j]) {
                        arr1[i][j] = arr1[i][fw] + arr1[fw][j];
                        trace[i][j] = fw ; // trace 배열에 전 노드 저장
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr1 = new int[N][N];
        trace = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(arr1[i], INF);
            Arrays.fill(trace[i], -1);
            arr1[i][i] = 0;
        }
        // 20000001

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken()) ;
            trace[s][e] = s;
            trace[e][s] = e;
            arr1[s][e] = arr1[e][s] = l;
        }
        // 함수 실행
        floydWarshall();
        
        // 조건에 맞게 출력하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                
                if (i == j) {
                    sb.append("- ");
                    continue;
                }
                
                int tmp = trace[i][j];

                if (i == tmp) {
                    sb.append(j + 1).append(" ");
                    continue;
                }
                // 경로 역추적 목적지 -> 출발지, 출발지 도착전 노드 출력해야 한다.
                int dist = 0;
                while (trace[i][tmp] != -1) {
                    dist = tmp;
                    tmp = trace[i][tmp];
                }
                sb.append(dist+1).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}