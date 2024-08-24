import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static long[] segment;
    public static int mod = 1_000_000_007;

    public static int getMid(int s, int e) {
        return (s + e) / 2;
    }

    public static long init(int start, int end, int idx) {

        if (start == end) return segment[idx] = arr1[start];
        int mid = getMid(start, end);
        return segment[idx] = (init(start, mid, idx * 2) * init(mid + 1, end, idx * 2 + 1)) % mod;
    }

    public static void update(int start, int end, int idx, int id, int val) {

        if (id < start || id > end) return;
        if (start == end) {
            segment[idx] = val;
            return;
        }

        int mid = getMid(start, end);
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);

        segment[idx] = (segment[idx * 2] * segment[idx * 2 + 1]) % mod;
    }

    public static long getTimes(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) return 1;
        if (left <= start && right >= end) return segment[idx];

        int mid = getMid(start, end);

        return (getTimes(start, mid, idx * 2, left, right)
                * getTimes(mid + 1, end, idx * 2 + 1, left, right)) % mod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr1 = new int[N + 1];
        segment = new long[(N + 1) * 4];

        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int ctrl = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (ctrl == 1) {
                update(1, N, 1, s, e);
                arr1[s] = e;
            } else {
                sb.append(getTimes(1, N, 1, s, e)).append("\n");
            }
        }
        System.out.println(sb);
    }
}