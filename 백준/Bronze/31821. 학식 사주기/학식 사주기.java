import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            answer += arr1[Integer.parseInt(br.readLine())-1];
        }
        System.out.println(answer);

    }
}