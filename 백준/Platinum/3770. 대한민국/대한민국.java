import java.io.*;
import java.util.*;

public class Main {
	public static long[] segment;

	public static class Node implements Comparable<Node>{
		int s, e;

		public Node(int s, int e){
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Node o) {
			if(s == o.s) {
				return e - o.e;
			}
			return s - o.s;
		}
	}

	public static void update(int start, int end, int idx, int id) {

		if (id < start || id > end)
			return;

		int mid = (start + end) / 2;

		if (start == end) {
			segment[idx] += 1;
			return;
		}

		update(start, mid, idx * 2, id);
		update(mid + 1, end, idx * 2 + 1, id);
		segment[idx] = segment[idx * 2] + segment[idx * 2 + 1];
	}

	public static long getInversionCount(int start, int end, int idx, int left, int right) {
		if (right < start || left > end)
			return 0;
		if (left <= start && right >= end)
			return segment[idx];

		int mid = (start + end) / 2;

		return getInversionCount(start, mid, idx * 2, left, right) + getInversionCount(mid + 1, end, idx*2+1, left, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			PriorityQueue<Node> pq = new PriorityQueue<>();
			long answer = 0;

			segment = new long[(M + 1) * 4];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				pq.offer(new Node(A, B));
			}
			

			while (!pq.isEmpty()) {
				Node nd = pq.poll();
				answer += getInversionCount(1, M, 1, nd.e + 1, M);
				update(1, M, 1, nd.e);
			}

			sb.append("Test case " + tc + ": " + answer).append("\n");

		}
		System.out.println(sb);
	}
}