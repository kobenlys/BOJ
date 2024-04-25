import java.io.*;
import java.util.*;

public class Main {
    public static long N, M;

    public static long dfs(long num) {

        if (num <= 1) {
            return 1;
        }

        long res = (num * dfs(num - 1)) % M;
        return res;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        if (N >= M) {
            System.out.println(0);
        } else {
            long num = N % M;
            System.out.println(dfs(num));
        }
    }
}