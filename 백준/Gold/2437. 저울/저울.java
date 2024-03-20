import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        int sum = 1;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // 1 1 2 3 6 7 30
        Arrays.sort(arr1);

        for (int i = 0; i < N; i++) {

            if (sum  < arr1[i]) {
                break;
            }
            sum += arr1[i];
          
        }
        System.out.println(sum);
    }
}