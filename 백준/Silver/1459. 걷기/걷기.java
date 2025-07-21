import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long W = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        long res1 = (X + Y) * W;

        long res2 = 0;

        if ((X + Y) % 2 == 0) {
            res2 = Math.max(X, Y) * S;
        } else {
            res2 = (Math.max(X, Y) - 1) * S + W;
        }
        long res3 = Math.min(X, Y) * S + (Math.max(X, Y) - Math.min(X,Y)) * W;

        System.out.println(Math.min(res1, Math.min(res2, res3)));

    }
}