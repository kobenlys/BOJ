import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sb.append(Math.min(a, Math.min(b, z))).append("\n");
        }
        System.out.println(sb);
    }
}