import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static long[] arr1;
    public static long[] segment;

    public static long init(int start, int end, int idx) {

        if (start == end) return segment[idx] = arr1[start];

        int mid = (start + end) / 2;
        return segment[idx] = init(start, mid, idx * 2) + init(mid + 1, end, idx * 2 + 1);
    }

    public static void updateSeg(int start, int end, int idx, int id, long val) {

        if (id < start || id > end) return;

        segment[idx] += val;
        if(start == end) return;

        int mid = (start + end) / 2;
        updateSeg(start, mid, idx * 2, id, val);
        updateSeg(mid + 1, end, idx * 2 + 1, id, val);
    }

    public static long getSum(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;
        return getSum(start, mid, idx * 2, left, right) + getSum(mid + 1, end, idx * 2 + 1, left, right);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr1 = new long[N + 1];
        segment = new long[(N + 1) * 4];

        for (int i = 1; i <= N; i++) {
            arr1[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int ctrl = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            long B = Long.parseLong(st.nextToken());


            if (ctrl == 1) {
                updateSeg(1, N, 1, A, B - arr1[A]);
                arr1[A] = B;
            } else {
                sb.append(getSum(1, N, 1, A, (int) B)).append("\n");

            }
        }
        System.out.println(sb);
    }
}