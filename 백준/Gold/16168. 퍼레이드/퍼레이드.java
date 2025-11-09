import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static boolean[] vi;
    public static List<List<Integer>> list = new ArrayList<>();

    public static int dfs(int idx) {
        int cnt = 0;
        for (int e : list.get(idx)) {
            if (!vi[e]) {
                vi[e] = true;
                cnt += dfs(e) + 1;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vi = new boolean[N + 1];
        // 오일러 경로 공식 사용
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.get(s).add(e);
            list.get(e).add(s);
        }
        
        // #1. 모든 노드가 이어져있는지
        if (N != dfs(1)) {
            System.out.println("NO");
            return;
        }

        int odd = 0;
        for (int i = 1; i <= N; i++) {
            if (list.get(i).size() % 2 != 0) {
                odd++;
            }
        }
        
        // #2. 오일러 경로 공식
        //        모든 노드의 차수가 짝수인 경우
        //        특정 두 노드만 차수가 홀수인 경우
        if (odd == 2 || odd == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}