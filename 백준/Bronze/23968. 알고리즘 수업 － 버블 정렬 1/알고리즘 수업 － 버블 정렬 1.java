import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] inputs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		
		// 배열 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;	//정렬 횟수
		int temp = 0;
		boolean isEnd = false;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (inputs[j] > inputs[j + 1]) {
					temp = inputs[j];
					inputs[j] = inputs[j + 1];
					inputs[j + 1] = temp;
					cnt++;
				}
				
				if (cnt >= K) {
					System.out.printf("%d %d", inputs[j], inputs[j + 1]);
					isEnd = true;
					return;
				}
			}
			
		}
		
		if (!isEnd) System.out.println(-1);
		br.close();
	}
}