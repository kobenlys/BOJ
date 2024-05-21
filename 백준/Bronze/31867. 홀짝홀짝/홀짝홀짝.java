import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int even = 0, odd = 0;
        for (int i = 0; i < N; i++) {
            int number = Character.getNumericValue(str.charAt(i));

            if (number % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        if (even == odd) {
            System.out.println(-1);
        } else {
            System.out.println(even > odd ? 0 : 1);
        }

    }
}