import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;
    public static boolean[] vi;
    public static ArrayList<Integer> res = new ArrayList<>();

    public static void dfs(int start) { // DFS 알고리즘

        if (start == N) { // 정답 확인
            boolean isSort = true;

            for (int i = 0; i < res.size(); i++) {
                int cnt = 0;
                for (int j = 0; j < i; j++) {
                    // i 번째 자리의 숫자가 왼쪽에 있는 숫자보다 클때 카운팅
                    if (res.get(i) < res.get(j)) {
                        cnt++;
                    }
                }
                // 문제에서 입력한 조건과 부합하는지 판단
                if (arr1[res.get(i)-1] != cnt) {
                    isSort = false;
                    break;
                }
            }
            if (isSort) {
                for (int e : res) {
                    System.out.print(e+" ");
                }
                System.exit(0);
            }
            return;
        }
            
        for (int i = 1; i <= N; i++) {
            if (!vi[i]) { // 방문안된 숫자 방문
                vi[i] = true; // 방문 처리
                res.add(i);
                dfs(start+1);
                res.remove(res.size() - 1);
                vi[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        vi = new boolean[N+1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 함수 실행
        dfs(0);
    }
}
