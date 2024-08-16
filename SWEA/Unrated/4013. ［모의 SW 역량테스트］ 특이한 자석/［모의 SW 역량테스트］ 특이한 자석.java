
import java.io.*;
import java.util.*;

public class Solution {
	public static int N;
	public static ArrayList<ArrayList<Integer>> arr1;
	public static boolean[] vi;
	
	public static void gear1(int dir) {
		
		boolean isDiff = false;
		vi[0] = true;
		
		if(!vi[1] && arr1.get(0).get(4) != arr1.get(1).get(0)) {
			isDiff = true;
		}
		
		if(dir == 1) { // 시계방향
			int num = arr1.get(0).get(7);
			arr1.get(0).add(0, num);
			arr1.get(0).remove(8);
		}else { // 반시계방향
			int num = arr1.get(0).get(0);
			arr1.get(0).add(num);
			arr1.get(0).remove(0);
		}
		
		if(isDiff) {
			dir = dir == 1? -1 : 1;
			gear2(dir);
		}
		
	}
	
	public static void gear2(int dir) {
		boolean isDiff1 = false;
		boolean isDiff2 = false;
		vi[1] = true;
		
		if(!vi[0] && arr1.get(1).get(0) != arr1.get(0).get(4)) {
			isDiff1 = true;
		}
		
		if(!vi[2] && arr1.get(1).get(4) != arr1.get(2).get(0)) {
			isDiff2 = true;
		}
		
		if(dir == 1) { // 시계방향
			int num = arr1.get(1).get(7);
			arr1.get(1).add(0, num);
			arr1.get(1).remove(8);
		}else { // 반시계방향
			int num = arr1.get(1).get(0);
			arr1.get(1).add(num);
			arr1.get(1).remove(0);
		}
		
		dir = dir == 1? -1 : 1;
		
		if(isDiff1) {
			gear1(dir);
		}
		
		if(isDiff2) {
			gear3(dir);
		}
	}

	public static void gear3(int dir) {
		
		boolean isDiff2 = false;
		boolean isDiff4 = false;
		vi[2] = true;
		
		if(!vi[1] && arr1.get(2).get(0) != arr1.get(1).get(4)) {
			isDiff2 = true;
		}
		
		if(!vi[3] && arr1.get(2).get(4) != arr1.get(3).get(0)) {
			isDiff4 = true;
		}
		
		if(dir == 1) { // 시계방향
			int num = arr1.get(2).get(7);
			arr1.get(2).add(0, num);
			arr1.get(2).remove(8);
		}else { // 반시계방향
			int num = arr1.get(2).get(0);
			arr1.get(2).add(num);
			arr1.get(2).remove(0);
		}
		
		dir = dir == 1? -1 : 1;
		
		if(isDiff2) {
			gear2(dir);
		}
		
		if(isDiff4) {
			gear4(dir);
		}
	}

	public static void gear4(int dir) {
		
		boolean isDiff = false;
		vi[3] = true;
		
		if(!vi[2] && arr1.get(3).get(0) != arr1.get(2).get(4)) {
			isDiff = true;
		}
		
		
		if(dir == 1) { // 시계방향
			int num = arr1.get(3).get(7);
			arr1.get(3).add(0, num);
			arr1.get(3).remove(8);
		}else { // 반시계방향
			int num = arr1.get(3).get(0);
			arr1.get(3).add(num);
			arr1.get(3).remove(0);
		}
		
		if(isDiff) {
			dir = dir == 1? -1 : 1;
			gear3(dir);
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		
		int T = Integer.parseInt(br.readLine());
		
		
		for(int tc=1; tc<=T; tc++) {
			
			int K = Integer.parseInt(br.readLine());
			int answer =0;
			arr1 = new ArrayList<>();
			
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				arr1.add(new ArrayList<>());
				int[] tmp = new int[8];
				for(int j=0; j<=7; j++) {
					tmp[(j+2) %8] = Integer.parseInt(st.nextToken());
				}
				
				for(int j=0; j<=7; j++) {
					arr1.get(i).add(tmp[j]);
				}
			}
			
			for(int i=0; i< K; i++) {
				st = new StringTokenizer(br.readLine());
				int gearNum = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				vi = new boolean[4];
				
				if(gearNum == 1) {
					gear1(dir);
				}else if(gearNum == 2) {
					gear2(dir);
				}else if(gearNum == 3) {
					gear3(dir);
				}else {
					gear4(dir);
				}
				
			}
			
			if(arr1.get(0).get(2) == 1) answer +=1;
			if(arr1.get(1).get(2) == 1) answer +=2;
			if(arr1.get(2).get(2) == 1) answer +=4;
			if(arr1.get(3).get(2) == 1) answer +=8;
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}