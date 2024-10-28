import java.io.*;
import java.util.*;

public class Main {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int cnt = 1;
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if(number == 1){

                answer += cnt;
                cnt++;
            }else{
                cnt = 1;
            }
        }

        System.out.println(answer);
    }
}