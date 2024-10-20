import java.io.*;
import java.util.*;

public class Main {

    public static int[] countDigit(int N) {

        int[] res = new int[10];
        int target = 1;

        while (N / target > 0) {

            int lower = N - (N / target) * target;
            int now = (N / target) % 10;
            int higher = N / (target * 10);

            for (int i = 0; i < 10; i++) {
                res[i] += higher * target;
            }

            for (int i = 0; i < now; i++) {
                res[i] += target;
            }

            res[now] += lower + 1;
            res[0] -= target;
            target *= 10;
        }


        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = countDigit(N);


        for (int i = 0; i < 10; i++) {
            System.out.print(arr1[i] + " ");
        }

    }
}