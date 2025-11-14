import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        long tmp = 1;
        tmp *= Integer.parseInt(br.readLine());
        tmp *= Integer.parseInt(br.readLine());
        tmp *= Integer.parseInt(br.readLine());
        int[] arr1 = new int[10];

        while (tmp > 0) {
            long num =  tmp % 10;
            arr1[(int) num]++;
            tmp /= 10;
        }

        for (int i = 0; i < 10; i++) {
            sb.append(arr1[i]).append("\n");
        }
        System.out.println(sb);
    }
}