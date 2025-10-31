import java.io.*;
import java.util.*;

public class Main {

    public static class Line implements Comparable<Line> {

        int start, end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            if (start == o.start) {
                return end - o.end;
            }
            return start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 1;
        PriorityQueue<Line> pq = new PriorityQueue<>();
        PriorityQueue<Integer> cache = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            pq.offer(new Line(S, E));
        }

        while (!pq.isEmpty()) {

            Line line = pq.poll();

            if (cache.isEmpty()) {
                cache.add(line.end);
                continue;
            }

            int tmpEnd = cache.peek();

            if (tmpEnd > line.start) {
                cache.add(line.end);
                answer = Math.max(answer, cache.size());
            } else {

                while (!cache.isEmpty() && cache.peek() <= line.start) {
                    cache.poll();
                }
                cache.add(line.end);
            }
        }

        System.out.println(answer);
    }
}