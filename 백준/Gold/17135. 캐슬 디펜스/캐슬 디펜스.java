import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, K, answer;
	public static boolean[] archor;
	public static int[][] arr1;
	
	public static class node implements Comparable<node>{
		int x, y, dist;
		public node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(node o) {
			if(dist == o.dist) {
				return x - o.x;
			}
			return dist -o.dist;
		}
	}
	
	
	public static void playArchor() {
		
		List<node> list = new ArrayList<>();
		List<node> res = new ArrayList<>();
		boolean[][] vi = new boolean[N][M];
		int round = 0;
		int moveD = 0;
		int cnt = 0;
		
		while(round++ != N) {
			
			
			for(node now : res) {
				
				if(!vi[now.y][now.x]) {
					cnt++;
					vi[now.y][now.x] = true;
				}
			}
			
			for(int t=0; t < M ; t++) {
				if(archor[t]) {
					
					for(int i=0; i< N-moveD ; i++) {
						for(int j=0; j< M ; j++) {
							
							if(arr1[i][j] == 1 && !vi[i][j]) {
								
								int d = Math.abs(i - N) + Math.abs(j - t) - moveD;
								if(d > K) continue;
								list.add(new node(j, i, d));
								
							}
						}
					}
					
					Collections.sort(list);
					if(!list.isEmpty()) {
						node now = list.get(0);
						res.add(now);
					}
					list.clear();
				}
			}
			moveD++;
		}
		
		for(node now : res) {
			
			if(!vi[now.y][now.x]) {
				cnt++;
				vi[now.y][now.x] = true;
			}
		}
		
		answer = Math.max(answer,  cnt);
	}
	
	
	public static void dfs(int start, int idx) {
		
		if(start == 3) {
			playArchor();
			return;
		}
		
		for(int i=idx; i<M; i++) {
			archor[i] = true;
			dfs(start+1, i+1);
			archor[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr1 = new int[N][M];
		archor = new boolean[M];
		
		for(int i=0; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0);
		System.out.println(answer);
	}
}