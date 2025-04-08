import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());

        int N, M, X, Y;
        String status, reading;
        String[] input;
        int count = 0;
        for(int i=0; i<cases; i++) {
            count = 0;
            input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            X = Integer.parseInt(br.readLine().replaceAll(" ", ""));
            Y = Integer.parseInt(br.readLine().replaceAll(" ", ""));
            status = br.readLine().replaceAll(" ", "");
            
            int index = 0;
            for(int j=0; j<N; j++) {
                while(j+M>=status.length()) {
                    status += status.charAt(index);
                    index++;
                }
                reading = "";
                for(int k=j; k<j+M; k++) {
                    reading += Character.toString(status.charAt(k));
                }
                if(Integer.parseInt(reading)<=Y && Integer.parseInt(reading)>=X) {
                    count++;
                }
            }
            System.out.println(count);
        }
        
    }
}