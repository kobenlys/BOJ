import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        char[] arr1 = new char[N];

        for (int i = 0; i < N; i++) {

            if (str.charAt(i) != 'A' && 26 - (str.charAt(i) - 'A') <= K) {
                int tmp = 26 - (str.charAt(i) - 'A');
                K -= tmp;
                arr1[i] = 'A';

            } else {
                arr1[i] = str.charAt(i);
            }
        }

        while (K-- > 0) {
            arr1[N - 1] = (char) ((arr1[N - 1] - 'A' + 1) % 26 + 'A');
        }

        for (char a : arr1) {
            sb.append(a);
        }
        System.out.print(sb);
    }
}