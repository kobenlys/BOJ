import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long now = 0;
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreElements()) {
			now += Integer.parseInt(st.nextToken());
		}
		
		int num = 0;
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreElements()) {
			num = Integer.parseInt(st.nextToken());
			
			if (num != 0) {
				now *= num;
			}
		}
		
		bw.write(Long.toString(now));
		bw.flush();
		bw.close();
		br.close();
	}
}