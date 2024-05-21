import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken())-1;
        int K = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            K /= 2;
        }
        System.out.println(K);
    }
}