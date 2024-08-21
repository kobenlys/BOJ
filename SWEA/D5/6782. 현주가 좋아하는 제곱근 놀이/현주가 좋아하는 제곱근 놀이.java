import java.io.*;
import java.util.*;

public class Solution {
	public static long N;
	
	public static int calcNum() {
		int cnt = 0;
		
		while(N != 2) {
			
			double rootNum = Math.sqrt(N);
			
			if(Math.floor(rootNum) == rootNum) {
				N = (long) rootNum;
				cnt++;
				
			}else {
				long tmp = ((long) rootNum + 1) * ((long) rootNum + 1);
				cnt += tmp - N;
				N = tmp;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = Long.parseLong(br.readLine());
			int answer =calcNum();
			
			sb.append("#").append(tc).append(" ")
			.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}