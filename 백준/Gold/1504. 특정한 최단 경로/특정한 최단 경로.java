import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] arr1;
    public static int N, INF = 640000001;

    public static void algorithm() { // 구현
        // 플로이드 워셜 알고리즘
        for (int fw = 0; fw < N; fw++) { // 거쳐가는 노드
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr1[i][j] = Math.min(arr1[i][j], arr1[i][fw] + arr1[fw][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        arr1 = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(arr1[i], INF);
            arr1[i][i] = 0;
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            // 본문 중 "방향성이 없는 그래프" 즉 양방향성 그래프이다.
            arr1[s][e] = arr1[e][s] = l;
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int s1 = Integer.parseInt(st.nextToken()) - 1;
        int s2 = Integer.parseInt(st.nextToken()) - 1;

        algorithm(); // 함수 호출

        // "양방향" 그래프이기 때문에
        // 1 -> s1 ->s2 -> N , 1 -> s2 -> s1 -> N 두 경우를 구해야함
        // 문제에서 지정한 처음과 끝 (1,N)은 고정한다.

        int res1 = arr1[0][s1] + arr1[s1][s2] + arr1[s2][N - 1];
        int res2 = arr1[0][s2] + arr1[s2][s1] + arr1[s1][N - 1];
        int ans = Math.min(res1, res2);
        // 두 경우 모두 이동간 갈수없는곳(INF)가 있다면
        // 위에서 더해줬기 때문에 ans는 INF보다 같거나 크다
        System.out.println(ans >= INF ? -1 : ans);
    }
}