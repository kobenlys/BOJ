import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        char[] arr1 = br.readLine().toCharArray();

        for (int i = 0; i < N-1; i++) {
            String input = br.readLine();
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j] != '?' && arr1[j] != input.charAt(j)) {
                    arr1[j] = '?';
                }
            }
        }
        System.out.println(arr1);
    }
}