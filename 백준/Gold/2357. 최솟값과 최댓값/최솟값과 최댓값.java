import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static node[] segment; // 최대, 최소를 한번에 업데이트 할때 필요하다.

    // 최대, 최소 기록하는 클래스
    public static class node {
        int max, min;

        public node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    // 세그먼트트리 업데이트
    public static node init(int start, int end, int idx) {

        if (start == end) return segment[idx] = new node(arr1[start], arr1[start]);
        int mid = (start + end) / 2;

        node left = init(start, mid, idx * 2);
        node right = init(mid + 1, end, idx * 2 + 1);
        // 최대, 최소를 현재 인덱스에 입력한다.
        return segment[idx] = new node(Math.max(left.max, right.max), Math.min(left.min, right.min));
    }

    // 세그먼트트리 범위 내 최소, 최대 구하기
    public static node getMinMax(int start, int end, int idx, int left, int right) {
        // 범위 밖이라면, 최대 최소 연산 제외한다
        if (right < start || left > end) return new node(0, Integer.MAX_VALUE);

        // 범위 내라면 이미 구해진 값 리턴하기
        if (left <= start && right >= end) return segment[idx];
        int mid = (start + end) / 2;

        // 좌 우 트리의 최대 최소 구하기
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

        // 세그먼트 트리 초기화하기
        init(1, N, 1);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 범위 최소, 최대값 출력하기
            node res = getMinMax(1, N, 1, s, e);
            sb.append(res.min).append(" ").append(res.max).append("\n");
        }
        System.out.print(sb);
    }
}