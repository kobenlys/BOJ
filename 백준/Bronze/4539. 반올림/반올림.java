import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String x;
	
		for(int i = 0; i < n; i++) {
			
			x = br.readLine();
			int[] arr = new int[x.length()]; 

			for(int j = 0; j < x.length(); j++) {
				
				arr[j] = 0;
				arr[j] = x.charAt(j) - 48;
			}
		
			for(int k = x.length() - 1; k >= 1; k--) {		
				
				if(arr[k] > 4)
					arr[k - 1] += 1;
				
				arr[k] = 0;
			}
			
			for(int p = 0; p < x.length(); p++)
				System.out.print(arr[p]);
			
			System.out.println();
		}
	}

}