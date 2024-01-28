import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static String res = "";
    public static int[] arr1;
    public static boolean[] vi;
    public static ArrayList<String> ans = new ArrayList<>();
    public static HashMap<String, Boolean> map = new HashMap<>();

    public static void dfs(int start) {

        if (start == M) {
            // HashMap으로 중복제거
            if (!map.containsKey(res)) {
                ans.add(res);
                map.put(res, true);
            }
            return;
        }

        for (int i = 0; i < N; i++) { // 모든 경우의 조합 만들기
            if (!vi[i]) {
                String tmp = res;
                res += res.isEmpty() ? arr1[i] : " " + arr1[i];
                vi[i] = true;
                dfs(start + 1);
                res = tmp;
                vi[i] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        vi = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 숫자를 사전순으로 정렬, 문자를 사전순으로 하면 숫자와 개념이 다르게 나온다.
        Arrays.sort(arr1);

        dfs(0);
        for (String e : ans) {
            sb.append(e).append("\n");
        }
        System.out.print(sb);
    }
}
