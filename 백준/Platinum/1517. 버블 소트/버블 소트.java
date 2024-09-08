import java.io.*;
import java.util.*;

public class Main {
    public static Node[] arr1;
    public static int[] segment;

    public static class Node implements Comparable<Node> {
        int num, idx;

        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return num - o.num;
        }
    }

    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || id > end) return;
        if (start == end) {
            segment[idx] = val;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);
        segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
    }

    public static int getInversion(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;

        return getInversion(start, mid, idx * 2, left, right)
                + getInversion(mid + 1, end, idx * 2 + 1, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
        arr1 = new Node[N];
        segment = new int[(N + 1) * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            arr1[i] = new Node(number, i + 1);
        }

        Arrays.sort(arr1);

        for (int i = 1; i <= N; i++) {
            Node tmp = arr1[i - 1];

            cnt += getInversion(1, N, 1, tmp.idx, N);
            update(1, N, 1, tmp.idx, 1);
        }

        System.out.println(cnt);
    }
}
