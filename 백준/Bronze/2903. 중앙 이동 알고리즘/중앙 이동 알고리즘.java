import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int res = 9;
        int cnt = 5;

        if (N == 1) {
            System.out.println(9);
        } else {
            for (int i = 0; i < N - 1; i++) {
                res = res * 4 - (cnt * 2 + 1);
                cnt = cnt * 2 - 1;
            }
            System.out.println(res);
        }
    }
}