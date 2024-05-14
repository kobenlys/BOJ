import java.io.*;
import java.util.*;

public class Main {
    public static long N, P, Q, Y, X;
    public static HashMap<Long, Long> map = new HashMap<>();

    public static long dfs(long N) {

        if (!map.containsKey(N) && N == 0) {
            map.put(N, 1L);
            return map.get(N);
        }

        if (!map.containsKey(N)) {
            map.put(N, dfs(Math.max(0, N / P - X)) + dfs(Math.max(0, N / Q - Y)));
        }

        return map.get(N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        System.out.println(dfs(N));
    }
}