import java.io.*;
import java.util.*;

public class Main {
	
	public static class node implements Comparable<node>{
		int id, time, prio;
		public node(int id, int time, int prio) {
			this.id = id;
			this.time = time;
			this.prio = prio;
		}
		
		@Override
		public int compareTo(node o) {
			if (o.prio == prio) {
				return id - o.id;
			}
			return o.prio - prio;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int time = 0;
		PriorityQueue<node> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.offer(new node(a,b,c));
		}
		
		
		while(!pq.isEmpty() && ++time <= T) {	
			node nowProcess = pq.poll();
			
			sb.append(nowProcess.id).append("\n");
			nowProcess.prio -= 1;
			nowProcess.time -= 1;
			if(nowProcess.time == 0) continue;
			pq.offer(nowProcess);
		}
		
		System.out.println(sb);		
	}
}