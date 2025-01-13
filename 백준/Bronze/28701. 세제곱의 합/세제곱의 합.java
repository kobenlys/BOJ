import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        long answer1 = 0;
        long answer3 = 0;

        for (int i = 1; i <= N; i++) {
            answer1 += i;
            answer3 += (long) Math.pow(i, 3);
        }
        System.out.println(answer1);
        System.out.println(answer1 * answer1);
        System.out.println(answer3);
    }
}