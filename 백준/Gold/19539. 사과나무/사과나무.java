import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int num1 = 0;
        int num2 = 0;
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            sum += number;
            num2 += number / 2;
            num1 += number % 2;
        }

        // System.out.println(sum + " "+ two + " "+one);
        if (sum % 3 > 0 || num1 > num2) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");
    }
}