import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        if (A == 0) {
            if (B == 0) {
                System.out.println("=");
            } else if (B == 5) {
                System.out.println("<");
            } else {
                System.out.println(">");
            }
            System.exit(0);
        }

        if (A == 2) {
            if (B == 2) {
                System.out.println("=");
            } else if (B == 0) {
                System.out.println("<");
            } else {
                System.out.println(">");
            }
            System.exit(0);
        }

        if (A == 5) {
            if (B == 5) {
                System.out.println("=");
            } else if (B == 2) {
                System.out.println("<");
            } else {
                System.out.println(">");
            }
            System.exit(0);
        }


        if (B == 0 || B == 2 || B == 5) {
            System.out.println("<");
        } else {
            System.out.println("=");
        }
    }
}