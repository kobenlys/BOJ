import java.io.*;
import java.util.*;

public class Solution {
	public static int answer;
	public static List<Node> player;
	public static List<Stair> stair;

	public static class Node {
		int x, y, time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static class Stair {
		int x, y, len;

		public Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}
	
	// A,B팀이 서로다른 계단을 사용할때
	public static void findMinTime(List<Integer> list) {

		PriorityQueue<Integer> qu1 = new PriorityQueue<>();
		PriorityQueue<Integer> qu2 = new PriorityQueue<>();

		for (int i = 0; i < player.size(); i++) {
			Node nd = player.get(i);
			if (!list.contains(i)) {
				int dist = Math.abs(stair.get(1).x - nd.x)
						+ Math.abs(stair.get(1).y - nd.y) + 1;
				qu2.add(dist);
			} else {
				int dist = Math.abs(stair.get(0).x - nd.x)
						+ Math.abs(stair.get(0).y - nd.y) + 1;
				qu1.add(dist);
			}
		}

		int time = 0;
		int max = 0;
		List<Integer> tmp = new ArrayList<>();
		
		while (!qu1.isEmpty()) {
			// 계단을 탈출시 제거
			for (int i = 0; i < tmp.size(); i++) {
				if (tmp.get(i) <= time) {
					tmp.remove(i);
					i--;
				}
			}
			
			// 현재 시간보다, 계단에 도착한 시간이 더 빠르다면 차례대로 계단에 넣기
			while (!qu1.isEmpty() && qu1.peek() <= time) {
				if (tmp.size() == 3)
					break;
				qu1.poll();
				// 다음 탈출시간 지정하기.
				tmp.add(time + stair.get(0).len);
			}
			time++;
		}

		for (int e : tmp) {
			max = Math.max(max, time + (e - time));
		}
		
		// 위와 동일
		time = 0;
		tmp = new ArrayList<>();
		while (!qu2.isEmpty()) {

			for (int i = 0; i < tmp.size(); i++) {
				if (tmp.get(i) <= time) {
					tmp.remove(i);
					i--;
				}
			}
			
			while (!qu2.isEmpty() && qu2.peek() <= time) {
				if (tmp.size() == 3)
					break;
				qu2.poll();
				tmp.add(time + stair.get(1).len);
			}
			time++;
		}

		for (int e : tmp) {
			max = Math.max(max, time + (e - time));
		}
		// 값 집계하기
		answer = Math.min(answer, max);
	}

	public static void dfs(int start, int idx, int target, List<Integer> list) {

		// 조합 완성시
		if (start == target) {
			List<Integer> tmp = new ArrayList<>();

			for (int i = 0; i < player.size(); i++) {
				if (!list.contains(i)) {
					tmp.add(i);
				}
			}

			// 출구를 다르게 해서 적은 시간을 구한다.
			findMinTime(tmp);
			findMinTime(list);
			return;
		}

		// 조합으로 특정 출구로 가는 사람들 선택하기
		for (int i = idx; i < player.size(); i++) {
			list.add(i);
			dfs(start + 1, i + 1, target, list);
			list.remove(list.size() - 1);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			player = new ArrayList<>();
			stair = new ArrayList<>();
			answer = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					
					if (num == 1) { // 플레이어
						player.add(new Node(j, i, 0));
					} else if (num != 0) { // 계단위치
						stair.add(new Stair(j, i, num));
					}
				}
			}

			for (int i = 0; i <= player.size() / 2; i++) {
				dfs(0, 0, i, new ArrayList<>());
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}