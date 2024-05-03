import java.io.*;
import java.util.*;

public class Main {

    public static boolean isCheck(int num) {

        String str = String.valueOf(num);
        if (str.length() == 1) return true;

        int a = Character.getNumericValue(str.charAt(0));
        int b = Character.getNumericValue(str.charAt(1));
        int tmp = a - b;

        for (int i = 1; i < str.length() - 1; i++) {
            a = Character.getNumericValue(str.charAt(i));
            b = Character.getNumericValue(str.charAt(i + 1));
            if (a - b != tmp) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 1; i <= N; i++) {

            if (isCheck(i)) {
                answer++;
            }
        }
        System.out.print(answer);
    }
}