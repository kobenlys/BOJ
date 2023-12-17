import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = {0, 1, 2};
        int cnt = 0;
        int milk = 0;
        st = new StringTokenizer(br.readLine(), " ");

        while (st.hasMoreElements()) {
            int store = Integer.parseInt(st.nextToken());
            if (store == arr1[cnt]) {
                cnt++;
                milk++;
                if (cnt == 3) {
                    cnt = 0;
                }
            }
        }
        System.out.print(milk);
    }
}