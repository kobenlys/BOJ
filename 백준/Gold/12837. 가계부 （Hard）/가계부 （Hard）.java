import java.io.*;
import java.util.*;

public class Main {
    public static long[] segment;

    public static void update(int start, int end, int idx, int id, long val) {

        if (id < start || id > end) return;
        segment[idx] += val;
        if (start == end) return;
        int mid = (start + end) / 2;

        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
    }

    private static long getSum(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;

        return getSum(start, mid, idx * 2, left, right)
                + getSum(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        segment = new long[(N + 1) * 4];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int ctrl = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if (ctrl == 1) {
                long x = Long.parseLong(st.nextToken());
                update(1, N, 1, p, x);
            } else {
                int q = Integer.parseInt(st.nextToken());
                sb.append(getSum(1, N, 1, p, q)).append("\n");
            }
        }
        System.out.println(sb);
    }
}