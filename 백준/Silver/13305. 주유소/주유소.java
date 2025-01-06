import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long[] len = new long[N];
        long[] cost = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            len[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        cost[N - 1] = Integer.MAX_VALUE;

        int pos = 0;
        long answer = 0;

        while (pos != N - 1) {

            int tmp = pos;

            if (cost[pos] > cost[pos+1]) {
                answer += cost[pos] * len[pos];
                pos++;
                continue;
            }

            for (int i = pos + 1; i <= N - 1; i++) {
                if (cost[tmp] <= cost[i]) {
                    answer += cost[tmp] * len[i-1];
                    pos = i;
                } else {
                    answer += cost[tmp] * len[i - 1];
                    pos++;
                    break;
                }
            }
        }

        System.out.println(answer);

    }
}