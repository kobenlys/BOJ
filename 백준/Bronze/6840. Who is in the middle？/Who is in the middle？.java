import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr1 = new int[3];
        for (int i = 0; i < 3; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr1);
        System.out.println(arr1[1]);
    }
}
