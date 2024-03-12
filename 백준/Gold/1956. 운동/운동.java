import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int V, E, INF =Integer.MAX_VALUE;
    public static int[][] arr1;

    public static void floydWarshall() {
        // 플로이드워셜 알고리즘 O(V^3)
        for (int fw = 0; fw < V; fw++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    if(i == j) continue;
                    if(arr1[i][fw] == INF || arr1[fw][j] == INF) continue;

                    if (arr1[i][j] > arr1[i][fw] + arr1[fw][j]) {
                        arr1[i][j] = arr1[i][fw] + arr1[fw][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;

        arr1 = new int[V][V];

        for (int i = 0; i < V; i++) {
            Arrays.fill(arr1[i], INF);
            arr1[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            arr1[s][e] = l;
        }

        floydWarshall();

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                // 갈수 없거나, 되돌아올수 있는 경로가 없다면 제외
                if (arr1[i][j] == INF || arr1[j][i] == INF) continue;
                // 집 출발 -> 체육관 -> 집 도착 이 가능해야함
                // i -> j + j -> i 의 값을 구하고 최솟값 도출
                min = Math.min(min, arr1[i][j] + arr1[j][i]);
            }
        }

        System.out.print(min == INF ? -1 : min);
    }
}