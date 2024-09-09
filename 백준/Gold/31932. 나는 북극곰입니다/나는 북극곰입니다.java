import java.io.*;
import java.util.*;

public class Main{
	public static int N, M;
	public static List<List<Node>> list = new ArrayList<>();

	public static class Node implements Comparable<Node> {
		int goal, val, limit;

		public Node(int goal, int val, int limit) {
			this.goal = goal;
			this.val = val;
			this.limit = limit;
		}

		@Override
		public int compareTo(Node o) {
			return limit - o.limit;
		}
	}

	public static boolean dijkstra(int fish) {

		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = fish;
		pq.offer(new Node(1, fish, 0));

		while (!pq.isEmpty()) {

			Node now = pq.poll();
			if (dist[now.goal] < now.val)
				continue;

			for (Node tmp : list.get(now.goal)) {
				if (tmp.limit < dist[now.goal] + tmp.val)
					continue;

				if (dist[tmp.goal] > dist[now.goal] + tmp.val) {
					dist[tmp.goal] = dist[now.goal] + tmp.val;
					pq.offer(new Node(tmp.goal, dist[tmp.goal], -1));
				}
			}
		}
		return dist[N] != Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			list.get(s).add(new Node(e, v, t));
			list.get(e).add(new Node(s, v, t));
		}

		int left = 0, right = 1_000_000_000;
		int answer = -1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (dijkstra(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(answer);
	}
}