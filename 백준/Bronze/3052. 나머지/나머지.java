import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr1 = new int[42];
        int cnt = 0;

        for (int i = 0; i < 10; i++) {
            int n = Integer.parseInt(br.readLine());
            int flag = n % 42;

            if (arr1[flag] == 0) {
                arr1[flag] = 1;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}


