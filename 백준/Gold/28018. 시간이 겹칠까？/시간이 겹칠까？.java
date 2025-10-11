import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] diff = new int[1_000_002];
        int[] prefix = new int[1_000_002];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            diff[s] += 1;
            diff[e + 1] -= 1;
        }

        for (int i = 1; i < 1_000_001; i++) {
            prefix[i] = prefix[i - 1] + diff[i];
        }

        int Q = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int time = Integer.parseInt(st.nextToken());
            sb.append(prefix[time]).append("\n");
        }
        System.out.println(sb);
    }
}