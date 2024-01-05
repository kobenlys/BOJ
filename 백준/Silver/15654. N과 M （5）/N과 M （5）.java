import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int M;
    public static int[] arr1;
    public static boolean[] vi;
    public static ArrayList<Integer> res = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int start) {

        if (start == M) {
            for (int i = 0; i < res.size(); i++) {
                sb.append(res.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!vi[i]) { // 방문하지 않는 노드 라면 방문
                vi[i] = true;
                res.add(arr1[i]);
                dfs(start+1); // 재귀 호출
                // 함수 탈출 시, 다음 연산을 위해 초기화.
                vi[i] = false;
                res.remove(res.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        vi = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬, DFS 함수 호출
        Arrays.sort(arr1);
        dfs(0);
        System.out.print(sb);
    }
}
