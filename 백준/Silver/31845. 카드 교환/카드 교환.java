import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] arr1 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i <N ; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        int right = N - 1;
        int left = 0;
        while (M-- > 0) {

            if (left > right) {
                break;
            }

            if (arr1[right] > 0) {
                answer += arr1[right--];
                left++;
            } else {
                break;
            }
        }
        System.out.println(answer);
    }
}