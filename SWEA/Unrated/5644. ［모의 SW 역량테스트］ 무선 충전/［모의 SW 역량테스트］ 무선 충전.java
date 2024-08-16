import java.io.*;
import java.util.*;

public class Solution {
	public static int btCnt, answer;
	public static ArrayList<blueTooth> btList;
	public static ArrayList<blueTooth> red;
	public static ArrayList<blueTooth> blue;

	public static class blueTooth implements Comparable<blueTooth> {
		int x, y, num, dist, power;

		public blueTooth(int x, int y, int num, int dist, int power) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dist = dist;
			this.power = power;
		}

		@Override
		public int compareTo(blueTooth o) {
			return o.power - power;
		}
	}

	public static void cntScore() {
		int max = 0;
		
		if(red.size() > blue.size()) {
			
			for (int i = 0; i < red.size(); i++) {
				blueTooth r = red.get(i);
				max = Math.max(max, r.power);
				
				for (int j = 0; j < blue.size(); j++) {
					blueTooth b = blue.get(j);
					max = Math.max(max, b.power);
					
					if (r.num == b.num) continue;
					max = Math.max(max, r.power + b.power);
				}
			}
			
		}else {
			for (int i = 0; i < blue.size(); i++) {
				blueTooth r = blue.get(i);
				max = Math.max(max, r.power);
				
				for (int j = 0; j < red.size(); j++) {
					blueTooth b = red.get(j);
					max = Math.max(max, b.power);
					
					if (r.num == b.num) continue;
					max = Math.max(max, r.power + b.power);
				}
			}
		}

		answer += max;
		red.clear();
		blue.clear();
	}

	public static void findBtRed(int x, int y) {

		for (blueTooth bt : btList) {
			int d = Math.abs(bt.x - x) + Math.abs(bt.y - y);
			if (bt.dist >= d) {
				red.add(bt);
			}
		}
	}

	public static void findBtBlue(int x, int y) {

		for (blueTooth bt : btList) {
			int d = Math.abs(bt.x - x) + Math.abs(bt.y - y);
			if (bt.dist >= d) {
				blue.add(bt);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int[] dx = { 0, 0, 1, 0, -1 };
		int[] dy = { 0, -1, 0, 1, 0 };

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			btCnt = Integer.parseInt(st.nextToken());

			btList = new ArrayList<>();
			red = new ArrayList<>();
			blue = new ArrayList<>();

			answer = 0;

			int x1 = 1;
			int y1 = 1;
			int x2 = 10;
			int y2 = 10;

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			for (int i = 1; i <= btCnt; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				blueTooth bt = new blueTooth(x, y, i, d, p);
				btList.add(bt);
			}

			findBtBlue(x1, y1);
			findBtRed(x2, y2);
			cntScore();

			for (int i = 0; i < time; i++) {
				int redDir = Integer.parseInt(st1.nextToken());
				int blueDir = Integer.parseInt(st2.nextToken());

				x1 = x1 + dx[redDir];
				y1 = y1 + dy[redDir];

				x2 = x2 + dx[blueDir];
				y2 = y2 + dy[blueDir];

				findBtBlue(x1, y1);
				findBtRed(x2, y2);
				cntScore();
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}
}