import java.io.*;
import java.util.*;

public class Main {
    public static int M;
    public static int[] arr1, res;
    public static HashSet<String> set = new HashSet<>();
    public static StringBuilder sb = new StringBuilder();

    public static void dfs(int start, int idx) {

        if (start == M) {

            for (int i = 0; i < M; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        // before 을 사용하여 전값과 현재값이 같다면 패스하고 다른 숫자 탐색.
        int before = -1;
        for (int i = idx; i < arr1.length; i++) {
            if (before != arr1[i]) {
                res[start] = arr1[i];
                dfs(start + 1, i);
                before = arr1[i];
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        res = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        
        // 정렬하고 dfs한다.
        Arrays.sort(arr1);
        dfs(0, 0);

        System.out.print(sb);
    }
}