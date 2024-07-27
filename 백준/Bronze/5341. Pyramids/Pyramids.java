import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){

            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum +=i;
            }
            System.out.println(sum);
        }


    }
}
