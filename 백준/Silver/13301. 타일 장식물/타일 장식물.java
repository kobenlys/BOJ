import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int N = Integer.parseInt(br.readLine());

        long[] arr1 = new long[N + 2];
       

        if (N == 1) {
            System.out.println(4);
        } else if (N == 2) {
            System.out.println(6);
        } else {
            arr1[1] = 1;
            arr1[2] = 1;

            for (int i = 3; i <= N+1 ; i++) {
                arr1[i] = arr1[i - 1] + arr1[i - 2];
            }
            System.out.println(arr1[N]*2 + arr1[N+1]*2);
        }
    }
}