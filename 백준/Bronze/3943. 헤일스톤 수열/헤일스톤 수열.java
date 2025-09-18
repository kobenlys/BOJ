import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());


        for(int i = 0; i < T; i++) {
            long n = Long.parseLong(br.readLine());
            long maxVal = n;


            while(n != 1) {
                if(n % 2 == 0) {
                    n /= 2;
                } else {
                    n = n * 3 + 1;
                }

                maxVal = Math.max(maxVal, n);
            }
            sb.append(maxVal).append("\n");
        }

        System.out.print(sb.toString());
    }
}