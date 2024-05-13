
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for (int i = 1; i <= 10; i++) {
            ArrayList<Integer> arr1 = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1.add(Integer.parseInt(st.nextToken()));
            }

            int T = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "I");

            while (T-- > 0) {
                StringTokenizer st1 = new StringTokenizer(st.nextToken(), " ");
                int idx = Integer.parseInt(st1.nextToken());
                int times = Integer.parseInt(st1.nextToken());

                for (int j = idx; j < times+idx; j++) {
                    arr1.add(j, Integer.parseInt(st1.nextToken()));
                }
            }
            sb.append("#").append(i).append(" ");
            for (int j = 0; j < 10; j++) {
                sb.append(arr1.get(j)).append(" ");

            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}