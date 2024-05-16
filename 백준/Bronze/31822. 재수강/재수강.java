import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();

            for (int j = 0; j < 5; j++) {
                if (str.charAt(j) != tmp.charAt(j)) {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(N - cnt);

    }
}