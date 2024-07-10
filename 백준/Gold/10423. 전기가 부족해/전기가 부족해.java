import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, K;
	public static int[] parent;
	
	public static class node implements Comparable<node>{
		int s,e,cost;
		public node(int s, int e, int cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(node o) {
			return cost - o.cost;
		}
		
	}
	
	public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	public static boolean union(int x, int y) {
		int from = find(x);
		int to = find(y);
		
		if(from != to) {
			parent[to] = from;
			return false;
		}
		return true;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int answer =0;
		
		parent = new int[N+1];
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int i=0; i<=N; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int targetNode = Integer.parseInt(st.nextToken());
		
		for(int i=0; i< K-1; i++) {
			int nextNode = Integer.parseInt(st.nextToken());
			union(targetNode, nextNode);
		}
		
		for(int i=0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new node(s,e,w));
		}
		
		while(!pq.isEmpty()) {
			
			node now = pq.poll();
			
			if(!union(now.s, now.e)) {
				answer += now.cost;
			}
		}
		
		System.out.println(answer);
	}
}
