import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());
            int[] arr1 = new int[1001];
            int max = 0;
            int number = 0;


            for (int i = 0; i < N; i++) {
                int idx = Integer.parseInt(br.readLine());
                if(max <= ++arr1[idx]){
                    if(max == arr1[idx]){
                        number = Math.min(number, idx);
                        continue;
                    }
                    max = arr1[idx];
                    number = idx;
                }
            }
            sb.append(number).append("\n");
        }
        System.out.println(sb);
    }
}