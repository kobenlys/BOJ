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

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            D = D + D + 1;
            
            sb.append("#").append(t).append(" ");
            sb.append(N % D == 0 ? N / D : N / D + 1);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}