import java.io.*;
import java.util.*;

public class Main {

    public static boolean findK(int number, int K) {
        if(number / 10 == K || number % 10 == K){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int hour = 0;
        int minute = 0;
        int second = 0;
        int answer = 0;

        while(hour <= N){

            if(findK(hour, K)){
                answer++;
            } else if (findK(minute, K)) {
                answer++;
            } else if (findK(second, K)) {
                answer++;
            }

            second++;
            if (second == 60) {
                second = 0;
                minute++;
            }

            if (minute == 60) {
                minute = 0;
                hour++;
            }
        }

        System.out.println(answer);
    }
}