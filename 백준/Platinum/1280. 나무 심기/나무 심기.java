import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static long[] segmentTree;
    public static int[] segmentCnt;

    public static void update(int start, int end, int idx, int id, int treeNumber) {
        if (id < start || id > end) return;

        int mid = (start + end) / 2;
        segmentTree[idx] += treeNumber;
        segmentCnt[idx] += 1;
        if (start == end) {
            return;
        }

        update(start, mid, idx * 2, id, treeNumber);
        update(mid + 1, end, idx * 2 + 1, id, treeNumber);
    }

    public static long getRangeTree(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segmentTree[idx];

        int mid = (start + end) / 2;
        long target1 = getRangeTree(start, mid, idx * 2, left, right);
        long target2 = getRangeTree(mid + 1, end, idx * 2 + 1, left, right);
        return target1 + target2;
    }

    public static int getRangeCnt(int start, int end, int idx, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && right >= end) return segmentCnt[idx];

        int mid = (start + end) / 2;
        int target1 = getRangeCnt(start, mid, idx * 2, left, right);
        int target2 = getRangeCnt(mid + 1, end, idx * 2 + 1, left, right);
        return target1 + target2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int MAX_RANGE = 200_001;
        long answer = 1;
        segmentTree = new long[MAX_RANGE * 4];
        segmentCnt = new int[MAX_RANGE * 4];

        int firstTree = Integer.parseInt(br.readLine());
        update(0, MAX_RANGE, 1, firstTree, firstTree);

        for (int i = 1; i < N; i++) {
            int tree = Integer.parseInt(br.readLine());
            long maxTree = getRangeTree(0, MAX_RANGE, 1, tree + 1, MAX_RANGE);
            long minTree = getRangeTree(0, MAX_RANGE, 1, 0, tree - 1);

            int maxCnt = getRangeCnt(0, MAX_RANGE, 1, tree + 1, MAX_RANGE);
            int minCnt = getRangeCnt(0, MAX_RANGE, 1, 0, tree - 1);

            long tmp = 0;
            if (maxCnt != 0) {
                tmp += Math.abs(maxTree - (long) tree * maxCnt);
                tmp %= 1_000_000_007;
            }

            if (minCnt != 0) {
                tmp += Math.abs(minTree - (long) tree * minCnt);
                tmp %= 1_000_000_007;
            }

            answer = (tmp * answer) % 1_000_000_007;
            update(0, MAX_RANGE, 1, tree, tree);
        }

        System.out.println(answer);
    }
}