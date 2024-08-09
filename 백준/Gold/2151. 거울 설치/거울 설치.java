import java.util.*;
import java.io.*;

class Main{
	public static int N;
	public static char[][] map;
	public static ArrayList<ArrayList<point>> list;
	
	
	public static class node{
		int x, y, idx;
		public node(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	
	public static class point implements Comparable<point>{
		int goal, val;
		public point(int goal, int val) {
			this.goal = goal;
			this.val = val;
		}
		
		@Override
		public int compareTo(point o) {
			return val - o.val;
		}	
	}
	
	public static boolean isPossible(node p1, node p2, int dir) {
		
		// x same
		if(dir == 0) {
			
			int y= Math.min(p1.y, p2.y);
			int max = Math.max(p1.y, p2.y);
			
			for(int i = y; i<N; i++) {
				if(map[i][p2.x] == '*') return false;
				if(i == max) break;
			}
		}
		
		// y same
		if(dir == 1) {
			int x= Math.min(p1.x, p2.x);
			int max = Math.max(p1.x, p2.x);
			
			for(int i = x; i<N; i++) {
				if(map[p2.y][i] == '*') return false;
				if(i == max) break;
			}
		}
		return true;
	}
	
	
	public static int dijkstra(int s, int e) {
		int[] dist = new int[list.size()];
		Arrays.fill(dist, 100000000);
		dist[s] = 0;
		
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(s,0));
		
		while(!pq.isEmpty()) {
			
			point now = pq.poll();
			
			if(dist[now.goal] < now.val) continue;
			
			for(point pos1 : list.get(now.goal)) {
				
				if(dist[pos1.goal] > dist[now.goal] + pos1.val) {
					dist[pos1.goal] = dist[now.goal] + pos1.val;
					pq.offer(new point(pos1.goal, dist[pos1.goal]));
				}
			}
		}
		return dist[e];
	}
	

	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int startIdx = -1, endIdx = -1;
		int cnt = 0;
		
		map = new char[N][N];
		list = new ArrayList<>();
		ArrayList<node> mirror = new ArrayList<>();
		
		
		for(int i=0; i< N; i++) {
			String input = br.readLine();
			for(int j=0; j< N; j++) {
				char a = input.charAt(j);
				map[i][j] = a;
				if(a == '#') {
					if(startIdx == -1) {
						startIdx = cnt;
					}else {
						endIdx = cnt;
					}
				}
				
				if(a != '*' && a != '.') {
					mirror.add(new node(j,i,cnt++));
				}
			}
		}
		
		for(int i=0; i<cnt; i++) {
			list.add(new ArrayList<>());
		}
		
		mirror.sort((o1,o2) -> Integer.compare(o1.x,o2.x));
		
		for(int i=0; i < mirror.size(); i++) {
			node pos1 = mirror.get(i);
			
			for(int j=0; j < mirror.size(); j++) {
			
				if(i == j) continue;
				node pos2 = mirror.get(j);
				if(pos1.x == pos2.x && isPossible(pos1, pos2, 0)) {
					list.get(pos1.idx).add(new point(pos2.idx, 1));
				}
			}
		}
		
		mirror.sort((o1,o2) -> Integer.compare(o1.y,o2.y));
		
		for(int i=0; i < mirror.size(); i++) {
			node pos1 = mirror.get(i);
			
			for(int j=0; j < mirror.size(); j++) {
				if(i == j) continue;
				node pos2 = mirror.get(j);
				if(pos1.y == pos2.y && isPossible(pos1, pos2, 1)) {
					list.get(pos1.idx).add(new point(pos2.idx, 1));
				}
			}
		}
		
		int answer = dijkstra(startIdx, endIdx);
		System.out.println(answer-1);
	}
}