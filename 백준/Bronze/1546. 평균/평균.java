import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        double[] arr1 = new double[n];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);

        double m = arr1[n - 1];
        double sum = 0;
        double res = 0;

        for (int i = 0; i < arr1.length; i++) {
            sum += (arr1[i] / m) * 100;
        }

        res = sum / n;

        System.out.println(res);


    }
}
