import java.io.*;
import java.util.*;

public class Main {
    public static Node[] arr1;
    public static Node[] segment;

    public static class Node {
        int idx, value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    public static Node init(int start, int end, int idx) {

        if (start == end) return segment[idx] = new Node(arr1[start].idx, arr1[start].value);
        int mid = (start + end) / 2;

        Node t1 = init(start, mid, idx * 2);
        Node t2 = init(mid + 1, end, idx * 2 + 1);

        if (t1.value == t2.value) {
            if (t1.idx < t2.idx) {
                return segment[idx] = new Node(t1.idx, t1.value);
            } else {
                return segment[idx] = new Node(t2.idx, t2.value);
            }
        }
        return segment[idx] = t1.value < t2.value ? new Node(t1.idx, t1.value) : new Node(t2.idx, t2.value);
    }

    public static void update(int start, int end, int idx, int id, int val) {

        if (id < start || id > end) return;

        if (start == end) {
            segment[idx] = new Node(id, val);
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, id, val);
        update(mid + 1, end, idx * 2 + 1, id, val);

        Node tmp1 = segment[idx * 2];
        Node tmp2 = segment[idx * 2 + 1];

        if (tmp1.value == tmp2.value) {
            segment[idx] = new Node(tmp1.idx, tmp1.value);
        }else{
            segment[idx] = tmp1.value < tmp2.value ? new Node(tmp1.idx, tmp1.value) : new Node(tmp2.idx, tmp2.value);
        }
    }

    public static Node getMinIdx(int start, int end, int idx, int left, int right) {

        if(right < start || left > end) return new Node(-1, Integer.MAX_VALUE);
        if(left <= start && right >= end) return segment[idx];

        int mid = (start + end) / 2;

        Node tmp1 = getMinIdx(start, mid, idx * 2, left, right);
        Node tmp2 = getMinIdx(mid + 1, end, idx * 2 + 1, left, right);

        if (tmp1.value == tmp2.value) {
            return new Node(tmp1.idx, tmp1.value);
        }else{
            return tmp1.value < tmp2.value ? new Node(tmp1.idx, tmp1.value) : new Node(tmp2.idx, tmp2.value);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new Node[N + 1];
        segment = new Node[(N + 1) * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr1[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }
        init(1, N, 1);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(dir == 1){
                // update
                update(1, N, 1, A, B);
                arr1[A] = new Node(A, B);
            }else{
                Node ans = getMinIdx(1, N, 1, A, B);
                sb.append(ans.idx).append("\n");
            }
        }
        System.out.println(sb);
    }
}