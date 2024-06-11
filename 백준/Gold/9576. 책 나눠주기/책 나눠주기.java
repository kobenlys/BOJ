import java.io.*;
import java.util.*;

public class Main {
    public static int[] match;
    public static boolean[] vi;
    public static ArrayList<ArrayList<Integer>> arr1;

    public static boolean dfs(int num) {

        for (int i : arr1.get(num)) {
            if (!vi[i]) {
                vi[i] = true;

                // 아직 점유되지 않는 노드 또는 점유되었지만, 원래 점유자가 변경 가능하다면
                if (match[i] == 0 || dfs(match[i])) {
                    // 점유 표시 후 true 리턴
                    match[i] = num;
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            arr1 = new ArrayList<>(); // 노드별 간선 표시.
            match = new int[N + 1]; // 매칭 확인 배열
            vi = new boolean[N + 1]; // 방문 표시

            for (int i = 0; i <= M; i++) {
                arr1.add(new ArrayList<>());
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                // i번 사람 기준 j노드로 갈 수 있는 간선 표현
                for (int j = s; j <= e; j++) {
                    arr1.get(i).add(j);
                }
            }

            int answer = 0;
            // 사람마다 이분매칭 한다.
            for (int i = 1; i <= M; i++) {
                // 방문표시는 초기화 후 dfs실행한다.
                Arrays.fill(vi, false);
                if (dfs(i)) {
                    answer++; // 점유 가능한 사람수 카운팅
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}