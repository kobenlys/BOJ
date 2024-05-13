import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            
            int N = Integer.parseInt(br.readLine());
            int[] arr1 = new int[N + 2];   
            int upDiff = 0;
            int downDiff = 0;
            
            arr1[0] = arr1[N + 1] = -1;

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr1[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 1; j <= N; j++) {
                int left = arr1[j - 1];
                int right = arr1[j + 1];

                if (left != -1) {
                    upDiff = Math.max(upDiff, Math.max(0, arr1[j] - left));
                }

                if (right != -1) {
                    downDiff = Math.max(downDiff, Math.max(0, arr1[j] - right));
                }
            }

            sb.append("#").append(i).append(" ");
            sb.append(upDiff).append(" ").append(downDiff);
            sb.append("\n");

        }
        System.out.print(sb);
    }
}