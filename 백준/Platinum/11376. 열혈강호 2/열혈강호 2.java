import java.io.*;
import java.util.*;

public class Main {
    public static int[] match;
    public static boolean[] vi;
    public static ArrayList<ArrayList<Integer>> arr1;

    public static boolean dfs(int num) {

        for (int idx : arr1.get(num)) {

            if (!vi[idx]) {
                vi[idx] = true;

                if (match[idx] == 0 || dfs(match[idx])) {
                    match[idx] = num;
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
        vi = new boolean[M + 1];
        arr1 = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            while (t-- > 0) {
                int s = Integer.parseInt(st.nextToken());
                arr1.get(i).add(s);
            }
        }


        for (int i = 1; i <= N; i++) {

            for (int j = 0; j < 2; j++) {
                vi = new boolean[M + 1];
                if (dfs(i)) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}