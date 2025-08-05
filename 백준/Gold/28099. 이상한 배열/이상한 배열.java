import java.io.*;
import java.util.*;

public class Main {

    public static Map<Integer, Node> map;
    public static int[] arr1, segment;

    public static class Node {

        int minIdx, maxIdx;

        public Node(int minIdx, int maxIdx) {
            this.minIdx = minIdx;
            this.maxIdx = maxIdx;
        }
    }

    public static int init(int start, int end, int idx) {
        if (start == end) {
            return segment[idx] = arr1[start];
        }
        int mid = (start + end) >> 1;
        return segment[idx] = Math.max(init(start, mid, idx * 2),
            init(mid + 1, end, idx * 2 + 1));
    }

    public static int findMaxNumber(int start, int end, int idx, int left, int right) {

        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && right >= end) {
            return segment[idx];
        }

        int mid = (start + end) >> 1;
        return Math.max(findMaxNumber(start, mid, idx * 2, left, right),
            findMaxNumber(mid + 1, end, idx * 2 + 1, left, right));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            arr1 = new int[N + 1];
            segment = new int[(N + 1) * 4];
            String answer = "Yes";

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
                if (!map.containsKey(arr1[i])) {
                    map.put(arr1[i], new Node(i, -1));
                } else {
                    map.get(arr1[i]).maxIdx = i;
                }
            }
            
            init(1, N, 1);
            
            for (Map.Entry<Integer, Node> entry : map.entrySet()) {
                Node range = entry.getValue();
                if (range.maxIdx == -1) {
                    continue;
                }
                int max = findMaxNumber(1, N, 1, range.minIdx, range.maxIdx);
                if (arr1[range.minIdx] < max) {
                    answer = "No";
                    break;
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
