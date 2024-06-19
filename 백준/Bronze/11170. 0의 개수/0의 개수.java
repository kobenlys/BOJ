import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 1_000_000; i++) {
            String n = String.valueOf(i);
            int cnt = 0;
            for (int j = 0; j < n.length(); j++) {
                if (n.charAt(j) == '0') cnt++;
            }
            map.put(i, cnt);
        }

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int answer = 0;
            for (int i = A; i <= B; i++) {
                answer += map.get(i);
            }
            sb.append(answer).append("\n");
        }
        
        System.out.print(sb);
    }
}