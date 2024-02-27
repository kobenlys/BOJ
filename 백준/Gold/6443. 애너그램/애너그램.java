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
            return;
        }
        // 메모리 초과 방지 하면서 중복제거 할때는 '문자'의 개수를 배열에 입력하고
        // 개수만큼 사용하면 된다. / 기존 방문처리(vi)배열을 사용하면 중복제거는 안되면서
        // 메모리를 더 사용하게 된다.
        for (int i = 0; i < 26; i++) {
            if (arr1[i] > 0) {
                arr1[i]--; // 단어개수 사용 시 --
                stk.push((char)(i+'a'));
                dfs(start+1);
                stk.pop();
                arr1[i]++; // 단어개수 사용끝 ++
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder ans = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            str = br.readLine();
            arr1 = new int[26];
            // 문자열에 포함된 단어개수 입력
            for (int i = 0; i < str.length(); i++) {
                arr1[str.charAt(i)-'a']++;
            }
            dfs(0);
            while (!pq.isEmpty()) {
                ans.append(pq.poll()).append("\n");
            }
        }
        System.out.print(ans);
    }
}