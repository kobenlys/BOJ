import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {

            String str = br.readLine();

            if (str.contains("=")) {
                System.out.println("skipped");
                continue;
            }

            st = new StringTokenizer(str, "+");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(a + b);
        }
    }
}