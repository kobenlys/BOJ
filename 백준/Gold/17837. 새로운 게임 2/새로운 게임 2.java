import java.io.*;
import java.util.*;

public class Main {
	public static int N, K;
	public static int[][] map;
	public static node[][] arr1;
	public static horse[] horseSite;
	
	public static int[] dx = {1,-1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	public static class node{
		List<Integer> list = new ArrayList<>();
	}
	
	public static class horse{
		int x, y, num, dir;
		public horse(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	
	public static void updateCordi(horse now) {
		
		for(int i=0; i<arr1[now.y][now.x].list.size(); i++) {
			
			int num = arr1[now.y][now.x].list.get(i);
			horseSite[num].x = now.x;
			horseSite[num].y = now.y;
		}
		
	}
	
	public static void moveHorseWhite(horse now) {
		int nx = now.x + dx[now.dir];
		int ny = now.y + dy[now.dir];
		
		int idx = arr1[now.y][now.x].list.indexOf(now.num);
		
		for(int i=idx; i<arr1[now.y][now.x].list.size(); i++) {
			arr1[ny][nx].list.add(arr1[now.y][now.x].list.get(i));
		}
		
		for(int i=idx; i<arr1[now.y][now.x].list.size(); i++) {
			arr1[now.y][now.x].list.remove(idx);
			i--;
		}
		
		now.x = nx;
		now.y = ny;
		horseSite[now.num] = now;
		updateCordi(now);
	}
	
	
	public static void moveHorseRed(horse now) {
		int nx = now.x + dx[now.dir];
		int ny = now.y + dy[now.dir];
		
		int idx = arr1[now.y][now.x].list.indexOf(now.num);
		List<Integer> rev = new ArrayList<>();
		
		for(int i=idx; i<arr1[now.y][now.x].list.size(); i++) {
			rev.add(arr1[now.y][now.x].list.get(i));
		}
		
		Collections.reverse(rev);
		
		for(int i=0; i<rev.size(); i++) {
			arr1[ny][nx].list.add(rev.get(i));
		}
		
		
		for(int i=idx; i<arr1[now.y][now.x].list.size(); i++) {
			arr1[now.y][now.x].list.remove(idx);
			i--;
		}
		
		now.x = nx;
		now.y = ny;
		horseSite[now.num] = now;
		updateCordi(now);
	}
	
	public static void moveHorseBlue(horse now) {
		
		if(now.dir < 2) {
			now.dir = now.dir == 0? 1: 0;
		}else {
			now.dir = now.dir == 2? 3: 2;
		}
		
		int nx = now.x + dx[now.dir];
		int ny = now.y + dy[now.dir];
		
		if(nx < 0 || ny < 0|| ny >= N || nx >= N || map[ny][nx] == 2) {
			
			horseSite[now.num] = now;
			
		}else if(map[ny][nx] == 0) { // 흰색
			
			moveHorseWhite(now);
			
		}else {
			moveHorseRed(now);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//[문제 요약]
		// 말의 개수는 K개임, 또한 말은 번호가 붙어있음
		// 턴 한번은 1 ~ K번 말을 순서대로 이동 할 것임..
		// 한 말이 이동 할때 위에 올려져있는 말도 함께 이동함.
		// 한 턴에 진행중 말이 4개 이상 한곳에 쌓이면 게임 바로 종료
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		arr1 = new node[N][N];
		horseSite = new horse[K+1];
		
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				arr1[i][j] = new node();
			}
		}
		
			
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			arr1[y][x].list.add(i);
			horseSite[i] = new horse(x,y,i,dir); 
		}
		
		
		int round = 1;
		
		while(round <= 1000) {
			
			for(int i=1; i<=K; i++) {
				horse tmp = horseSite[i];
					
				int nx = tmp.x + dx[tmp.dir];
				int ny = tmp.y + dy[tmp.dir];
				
				if((nx < 0 || ny < 0|| ny >= N || nx >= N)  || map[ny][nx] == 2){
					moveHorseBlue(tmp);
					
				}else if (map[ny][nx] == 0){
					moveHorseWhite(tmp);
				} else {
					moveHorseRed(tmp);
				}
				
				tmp = horseSite[i];
				if(arr1[tmp.y][tmp.x].list.size() >= 4) {
					
					System.out.println(round);
					System.exit(0);
				}
			}
			round++;
		}
		
		
		System.out.println(-1);
		
		
	}
}