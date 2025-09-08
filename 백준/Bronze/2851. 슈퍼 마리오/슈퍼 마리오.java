import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int answer = 0;
        int diff = Integer.MAX_VALUE;

        int[] arr1 = new int[10];

        for (int i = 0; i < 10; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        int d = 10;
        for (int i = 0; i < 10; i++) {
            int sum = 0;
            for (int j = 0; j < d; j++) {
                sum += arr1[j];
            }

            int res = Math.abs(sum - 100);
            if (diff >= res) {

                if (diff == res) {
                    answer = Math.max(answer, sum);
                    continue;
                }

                diff = res;
                answer = sum;
            }

            d--;
        }

        System.out.println(answer);
    }
}