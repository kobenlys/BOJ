import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int A = 0;
        int B = 0;

        while (a != 0) {

            int num = a % 10;
            A = A * 10 + num;
            a = a / 10;
        }
        while (b != 0) {

            int num = b % 10;
            B = B * 10 + num;
            b = b / 10;
        }

        System.out.println(Math.max(A,B));
    }
}