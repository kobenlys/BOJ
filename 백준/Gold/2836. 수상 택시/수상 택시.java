import java.io.*;
import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {

        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (start == o.start) {
                return o.end - end;
            }
            return start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s > e) {
                list.add(new Node(e, s));
            }
        }
        if (list.isEmpty()) {
            System.out.println(M);
            return;
        }
        Collections.sort(list);
        long min = list.get(0).start;
        long max = list.get(0).end;
        long answer = M;

        // 1    5
        //   3       7
        //                     10    20


        for (Node nd : list) {

            if (max <= nd.start) {
                answer += (max - min) * 2L;
                min = nd.start;
                max = nd.end;
                continue;
            }
            max = Math.max(max, nd.end);
            min = Math.min(min, nd.start);
        }

        answer += (max - min) * 2L;
        System.out.println(answer);

    }
}