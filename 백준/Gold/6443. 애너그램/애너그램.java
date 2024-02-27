import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static String str;
    public static PriorityQueue<String> pq = new PriorityQueue<>();
    public static Stack<Character> stk = new Stack<>();
    public static int[] arr1;

    public static void dfs(int start) {

        if (start == str.length()) {
            StringBuilder sb = new StringBuilder();
            for (char c : stk) {
                sb.append(c);
            }
            pq.offer(sb.toString());
        }

        for (int i = 0; i < 26; i++) {
            if (arr1[i] > 0) {
                arr1[i]--;
                stk.push((char)(i+'a'));
                dfs(start+1);
                stk.pop();
                arr1[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            str = br.readLine();
            arr1 = new int[26];

            for (int i = 0; i < str.length(); i++) {
                arr1[str.charAt(i)-'a']++;
            }
            dfs(0);
            while (!pq.isEmpty()) {
                System.out.println(pq.poll());
            }
        }
    }
}