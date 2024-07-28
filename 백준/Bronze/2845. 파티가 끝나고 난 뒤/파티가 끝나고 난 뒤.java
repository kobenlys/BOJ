import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int res = A * B;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            int N = Integer.parseInt(st.nextToken());
            System.out.print(N - res + " ");
        }


    }
}
