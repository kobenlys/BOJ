import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[] minSegment;

    public static void minUpdate(int start, int end, int idx, int id, int val) {

        if (start > id || end < id) {
            return;
        }

        if (start == end) {
            if (minSegment[idx] == Integer.MAX_VALUE) {
                minSegment[idx] = val;
            } else {
                minSegment[idx] = Integer.MAX_VALUE;
            }
            return;
        }
        int mid = (start + end) / 2;
        minUpdate(start, mid, idx * 2, id, val);
        minUpdate(mid + 1, end, idx * 2 + 1, id, val);
        minSegment[idx] = Math.min(minSegment[idx * 2], minSegment[idx * 2 + 1]);
    }

    public static int getMinIdx(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && right >= end) {
            return minSegment[idx];
        }
        int mid = (start + end) >> 1;
        return Math.min(getMinIdx(start, mid, idx * 2, left, right),
            getMinIdx(mid + 1, end, idx * 2 + 1, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int nowPos = 1;

        minSegment = new int[(N + 1) * 4];
        Arrays.fill(minSegment, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            if ("1".equals(st.nextToken())) {
                minUpdate(1, N, 1, i, i);
            }
        }

        for (int i = 0; i < M; i++) {
            String[] command = br.readLine().split(" ");

            if ("1".equals(command[0])) {
                int num = Integer.parseInt(command[1]);
                minUpdate(1, N, 1, num, num);
            } else if ("2".equals(command[0])) {
                nowPos = (nowPos + Integer.parseInt(command[1])) % N;
                if (nowPos == 0) {
                    nowPos = N;
                }
            } else {
                int findIdx1 = getMinIdx(1, N, 1, nowPos, N);
                int answer = findIdx1 == Integer.MAX_VALUE ? -1 : findIdx1 - nowPos;

                if (answer == -1) {
                    int findIdx2 = getMinIdx(1, N, 1, 1, nowPos);
                    if (findIdx2 != Integer.MAX_VALUE) {
                        answer = N - nowPos + findIdx2;
                    }
                }

                sb.append(answer).append("\n");
            }
        }
        System.out.println(sb);
    }
}