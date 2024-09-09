import java.io.*;
import java.util.*;

public class Main {
	public static int N;
	public static int[] arr1;
	public static List<List<Integer>> list;

	public static class Node {
		int idx, color;

		public Node(int idx, int color) {
			this.idx = idx;
			this.color = color;
		}
	}

	public static boolean bfs(int idx) {
		Queue<Node> qu = new ArrayDeque<>();
		arr1[idx] = 1;
		qu.offer(new Node(idx, 1));

		while (!qu.isEmpty()) {

			Node tmp = qu.poll();

			for (int e : list.get(tmp.idx)) {

				if (arr1[e] == 0) {
					int rev = tmp.color == 1 ? 2 : 1;
					arr1[e] = rev;
					qu.offer(new Node(e,rev));
				}else {
					if(arr1[e] == arr1[tmp.idx]) return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int K = Integer.parseInt(br.readLine());

		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String answer = "YES";

			list = new ArrayList<>();
			arr1 = new int[N + 1];

			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				list.get(s).add(e);
				list.get(e).add(s);
			}
			
			for (int i = 1; i <= N; i++) {
				if(arr1[i] == 0 && !bfs(i)) {
					answer = "NO";
					break;
				}
			}
			
			System.out.println(answer);
		}
	}
}