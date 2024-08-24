import java.io.*;
import java.util.*;

public class Main {
    public static int[] arr1;
    public static long[] segment;
    public static int mod = 1_000_000_007;

    // mid 값 구하는 함수
    public static int getMid(int s, int e) {
        return (s + e) / 2;
    }

    // 세그먼트 트리 최초 입력 함수
    public static long init(int start, int end, int idx) {

        if (start == end) return segment[idx] = arr1[start];
        int mid = getMid(start, end);
        // 왼쪽 경로 * 오른쪽 경로 % mod -> 정수형 범위 넘지 않게 모듈러 이용
        return segment[idx] = (init(start, mid, idx * 2) * init(mid + 1, end, idx * 2 + 1)) % mod;
    }

    // 세그먼트 값 업데이트
    public static void update(int start, int end, int idx, int id, int val) {
        if (id < start || id > end) return;
        if (start == end) {
            // 리프 노드 도달시 value로 값 변경하기
            segment[idx] = val;
            return;
        }
        // 세그먼트 트리 업데이트 하기
        int mid = getMid(start, end);
        update(start, mid, idx * 2, id, val); // 세그먼트 트리 왼쪽
        update(mid + 1, end, idx * 2 + 1, id, val); // 세그먼트 트리 오른쪽
        segment[idx] = (segment[idx * 2] * segment[idx * 2 + 1]) % mod; // 계산
    }

    // 세그먼트 트리에서 left ~ right의 범위의 구간 곱 구하기.
    public static long getTimes(int start, int end, int idx, int left, int right) {
        if (left > end || right < start) return 1; // 범위를 벗어났다면
        if (left <= start && right >= end) return segment[idx]; // 범위 안에 있다면 값 리턴

        int mid = getMid(start, end);
        // 범위 탐색
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

            if (ctrl == 1) { // 값 변경하기
                update(1, N, 1, s, e);
                arr1[s] = e;
            } else { // 구간곱 구하기.
                sb.append(getTimes(1, N, 1, s, e)).append("\n");
            }
        }
        System.out.println(sb);
    }
}  