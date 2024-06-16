import java.io.*;
import java.util.*;

public class Main {
    public static int[] match;
    public static boolean[] vi;
    public static ArrayList<ArrayList<Integer>> arr1;

    public static boolean dfs(int num) {

        for (int e : arr1.get(num)) {

            if (!vi[e]) {
                vi[e] = true;
                // 점유가 되어있지 않거나, 이전 점유자가 다른 노드 선택 가능할때
                if (match[e] == 0 || dfs(match[e])) {
                    match[e] = num;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        match = new int[M + 1];
        arr1 = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) { // 입력
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            for (int j = 0; j < T; j++) {
                int e = Integer.parseInt(st.nextToken());
                arr1.get(i).add(e);
            }
        }
        
        // i번째 소가 점유 가능한 축사가 있는지 판단.
        for (int i = 1; i <= N; i++) {
            vi = new boolean[M + 1];
            if (dfs(i)) answer++;
        }
        System.out.println(answer);
    }
}