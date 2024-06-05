import java.io.*;
import java.util.*;

public class Main {

    public static long makeNum(int n) {
        long tmp = 1;
        while (n-- > 0) {
            tmp *= 26;
        }
        return tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        long answer = 0;
        answer += (makeNum(N) - 1) / 25 * (str.charAt(0) - 'a');
        answer++;

        for (int i = 1; i < str.length(); i++) {
            answer += (makeNum(N-i)) / 25 * (str.charAt(i) - 'a');
            answer++;
        }
        System.out.println(answer);
    }
}