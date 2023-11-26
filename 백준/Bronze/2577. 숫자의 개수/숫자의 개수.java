import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] arr1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr1 = new int[10];
        int sum = 1;

        for (int i = 0; i < 3; i++) {
            sum *= Integer.parseInt(br.readLine());
        }

        String str = String.valueOf(sum);
        
        // arr1의 n 자리에 +1 해준다.
        for (int i = 0; i < str.length(); i++) {
            int n = Character.getNumericValue(str.charAt(i));
            arr1[n] += 1;
        }

        for (int e : arr1) {
            System.out.println(e);
        }

    }
}
