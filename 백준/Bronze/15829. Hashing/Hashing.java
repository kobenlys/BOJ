import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int ans = 0;
        String input = br.readLine();

        for (int i = 0; i < L; i++) {
            ans += (int) ((input.charAt(i) - 96) * Math.pow(31, i)) % 1234567891;
        }
        System.out.print(ans);
    }
}