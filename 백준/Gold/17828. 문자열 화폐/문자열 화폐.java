import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stk = new Stack<>();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[] arr1 = new char[N];

        Arrays.fill(arr1, 'A');

        if (26 * N < M || N > M) {
            System.out.println('!');
        } else {

            M -= N;

            for (int i = N-1; i >= 0; i--) {
                if (M >= 26) {
                    M -= 25;
                    arr1[i] += 25;
                } else {
                    arr1[i] += M;
                    break;
                }
            }
            System.out.println(arr1);
        }
    }
}