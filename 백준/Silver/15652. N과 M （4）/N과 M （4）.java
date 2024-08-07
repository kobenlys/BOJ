import java.io.*;
import java.util.*;

public class Main {
    public static int N, M;
    public static StringBuilder sb;
    public static StringBuilder answer;

    public static void dfs(int num, int cnt, int idx) {

        if (cnt == M) {

            sb = new StringBuilder(String.valueOf(num));
            String t = sb.toString();
            
            for (int i = 0; i < sb.length(); i++) {
                answer.append(t.charAt(i)).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = idx; i <= N; i++) {
            dfs(num * 10 + i, cnt + 1, i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 4개에서 2개를 고르는 경우
        // 4! / (4-2)2! = 6
        dfs(0, 0, 1);


        System.out.println(answer);
    }
}