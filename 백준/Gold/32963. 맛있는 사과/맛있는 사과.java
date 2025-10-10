import java.io.*;
import java.util.*;

public class Main {

    public static int N, Q;
    public static List<Node> list;
    public static  Map<Integer, Integer> cache = new HashMap<>();

    public static class Node implements Comparable<Node> {

        int tasty, size, maxSizeCnt;

        public Node(int tasty, int size) {
            this.tasty = tasty;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            if (this.tasty == o.tasty) {
                return this.size - o.size;
            }
            return this.tasty - o.tasty;
        }
    }

    public static void preProcessing() {

        int maxPos = 0;


        for (int i = N - 1; i >= 0; i--) {

            Node apple = list.get(i);
            maxPos = Math.max(maxPos, apple.size);

            if (!cache.containsKey(apple.size)) {
                cache.put(apple.size, 1);
            } else {
                cache.put(apple.size, cache.get(apple.size) + 1);
            }
            apple.maxSizeCnt = cache.get(maxPos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        StringTokenizer st2;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken())));
        }

        Collections.sort(list);
        preProcessing();

        while (Q-- > 0) {
            int query = Integer.parseInt(br.readLine());

            int left = 0, right = N - 1;
            int idx = -1;

            while (left <= right) {

                int mid = (left + right) >> 1;
                Node apple = list.get(mid);

                if (query <= apple.tasty) {
                    idx = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            if (idx == -1) {
                sb.append(0).append("\n");
            } else {
                sb.append(list.get(idx).maxSizeCnt).append("\n");
            }
        }
        System.out.println(sb);
    }
}