import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String S = br.readLine();
		int result = 0;
		int i = 0;
		
		for(int index = 0; index < N; index++) {
			//'O' 일때 연산하고 보너스 점수를 늘린다.
			if(S.charAt(index) == 'O') {
				result += index + i++ + 1;
			}else {
				// 'X'일때 보너스 점수 초기화 한다.
				i = 0;
			}
		}
		
		System.out.println(result);
		
	}
	
}