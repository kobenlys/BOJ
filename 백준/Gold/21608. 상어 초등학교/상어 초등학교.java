import java.io.*;
import java.util.*;

public class Main{
	public static int N;
	public static int[][] arr1;
	public static int[][] friend;
	
	public static int[] dx = {0,0,-1,1};
	public static int[] dy = {-1,1,0,0};
	
	public static class node implements Comparable<node>{
		int x, y, cnt, stu;
		
		public node(int x, int y, int cnt, int stu) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.stu = stu;
		}
		
		@Override
		public int compareTo(node o) {
			if(stu ==  o.stu) {
				if(cnt == o.cnt) {
					if(y == o.y) {
						return x -o.x;
					}
					return y - o.y;
				}
				return o.cnt - cnt;
			}
			return o.stu - stu;
		}
	}
	
	public static int calcScore() {
		
		HashSet<Integer> set = new HashSet<>();
		
		int res = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				int num = arr1[i][j];
				int cnt = 0;
				set.clear();
				
				for(int k=0; k<4; k++) {
					set.add(friend[num][k]);
				}
				
				for(int k=0; k<4; k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					
					if(set.contains(arr1[ny][nx])) cnt++;
				}
				
				if(cnt == 1) res += 1;
				if(cnt == 2) res += 10;
				if(cnt == 3) res += 100;
				if(cnt == 4) res += 1000;
			}
		}
		
		return res;
	}
	
	public static void placeStudent(int num) {
		
		List<node> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		
		for(int k=0; k<4; k++) {
			set.add(friend[num][k]);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				int cnt = 0;
				int stu = 0;
				
				if(arr1[i][j] == 0) {
					
					
					for(int k=0; k<4; k++) {
						int nx = j + dx[k];
						int ny = i + dy[k];
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						
						if(arr1[ny][nx] == 0) cnt++;
						else {
							if(set.contains(arr1[ny][nx])) {
								stu++;
							}
						}
						
					}
					
					
					list.add(new node(j,i,cnt,stu));
				}
			}
		}
		
		
		Collections.sort(list);
		
		if(!list.isEmpty()) {
			
			node tmp = list.get(0);
			
			arr1[tmp.y][tmp.x] = num;
			
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr1 = new int[N][N];
		friend = new int[N*N+1][4];
		
		List<Integer> seq = new ArrayList<>();
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			seq.add(num);
			
			for(int j=0; j<4; j++) {
				friend[num][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int e : seq) {
			placeStudent(e);
		}
		
		
		
		System.out.println(calcScore());
	}
}