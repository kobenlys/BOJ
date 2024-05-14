import java.io.*;
import java.util.*;

public class Main {
    public static long N, P, Q;
    public static HashMap<Long, Long> map = new HashMap<>();

    public static long dfs(long N) {

        if (N == 0) {
            map.put(0L, 1L);
            return map.get(N);
        }
        // 피보나치 수열 DP 탑다운처럼 구현
        // long의 데이터는 배열로 담을 수 없으니 해쉬맵 사용.
        if (!map.containsKey(N)) {
            map.put(N, dfs(N / P) + dfs(N / Q));
        }
        return map.get(N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        
        // dfs + 출력
        System.out.println(dfs(N));
    }
}