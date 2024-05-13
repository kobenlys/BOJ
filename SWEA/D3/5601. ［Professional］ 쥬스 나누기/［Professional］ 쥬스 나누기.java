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
            sb.append("#").append(i).append(" ");
            for (int j = 0; j < N; j++) {
                sb.append(1).append("/").append(N).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}