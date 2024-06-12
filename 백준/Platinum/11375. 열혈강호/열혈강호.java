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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        match = new int[M + 1];
        vi = new boolean[M + 1];
        arr1 = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            for (int j = 0; j < time; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr1.get(i).add(tmp);
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(vi, false);
            if (dfs(i)) answer++;
        }
        System.out.println(answer);

    }
}