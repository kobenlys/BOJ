import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static node[] segment;

    public static class node {
        int max, min;

        public node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static node init(int start, int end, int idx) {

        if (start == end) return segment[idx] = new node(arr1[start], arr1[start]);
        int mid = (start + end) / 2;

        node left = init(start, mid, idx * 2);
        node right = init(mid + 1, end, idx * 2 + 1);
        return segment[idx] = new node(Math.max(left.max, right.max), Math.min(left.min, right.min));
    }

    public static node getMinMax(int start, int end, int idx, int left, int right) {

        if (right < start || left > end) return new node(0, Integer.MAX_VALUE);
        if (left <= start && right >= end) return segment[idx];
        int mid = (start + end) / 2;

        node l = getMinMax(start, mid, idx * 2, left, right);
        node r = getMinMax(mid + 1, end, idx * 2 + 1, left, right);
        return new node(Math.max(l.max, r.max), Math.min(l.min, r.min));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr1 = new int[N + 1];
        segment = new node[(N + 1) * 4];

        for (int i = 1; i <= N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            node res = getMinMax(1, N, 1, s, e);
            sb.append(res.min).append(" ").append(res.max).append("\n");
        }
        System.out.print(sb);
    }
}