import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr1;
    
    // 플로이드 워셜 알고리즘
    public static void floydWarshall() {

        for (int fw = 1; fw <= N; fw++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue; // 자신 제외
                    // i -> j 보다 i -> fw -> j가 더 빠르다면.
                    if (arr1[i][j] > arr1[i][fw] + arr1[fw][j] + 1) {
                        arr1[i][j] = arr1[i][fw] + arr1[fw][j] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> ans = new ArrayList<>();
        arr1 = new int[N + 1][N + 1];
        
        // 배열 초기화.
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                arr1[i][j] = 10000000;
            }
            arr1[i][i] = 0;
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (s == -1) break;
            arr1[s][e] = arr1[e][s] = 0; // 친구는 양방향 간선으로 표현
        }
        
        // 플로이드 워셜 알고리즘
        floydWarshall();

        int depth = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int tmp = 0;
            boolean isPossible = true;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (arr1[i][j] == Integer.MAX_VALUE) {
                    isPossible = false;     
                    break;
                }
                tmp = Math.max(tmp, arr1[i][j]);
            }
            
            // 일단 모든 사람이 이어져 있다면 1점 추가
            tmp++;
            // 모두 이어져 있고, 이전 점수가 현재 점수보다 크거나 같다면
            if (isPossible && depth >= tmp) {
                if (depth > tmp) ans.clear(); // 이전값 클리어
                depth = tmp;
                ans.add(i);
            }
        }

        // 출력하기
        Collections.sort(ans);
        sb.append(depth).append(" ").append(ans.size()).append("\n");
        for (int e : ans) {
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }
}